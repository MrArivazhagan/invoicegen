package com.project.invoicegen.service;

import com.project.invoicegen.model.BaseForm;
import com.project.invoicegen.model.CameraForm;
import com.project.invoicegen.model.ReverseSensorForm;
import com.project.invoicegen.util.QRCodeGenerator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    public byte[] generatePdfForCameraForm(CameraForm form, String baseUrl) {
        return generateCameraPdf(form, baseUrl, "camera");
    }

    public byte[] generatePdfForReverseSensorForm(ReverseSensorForm form, String baseUrl) {
        return generatePdf(form, baseUrl, "reverse-sensor");
    }

    public byte[] generatePdf(Object form, String baseUrl, String formType) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Generate QR Code
            String pdfUrl = baseUrl + "/api/" + formType + "/pdf/" + ((BaseForm) form).getId();
            byte[] qrCodeImage = qrCodeGenerator.generateQRCode(pdfUrl, 100, 100);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float pageHeight = page.getMediaBox().getHeight();
                float pageWidth = page.getMediaBox().getWidth();
                float margin = 50;

                // Add logo (top left)
                PDImageXObject logo = PDImageXObject.createFromFile("C:\\Users\\hp\\3D Objects\\invoicegen\\src\\main\\java\\com\\project\\invoicegen\\util\\logo.png", document);
                contentStream.drawImage(logo, margin-30, pageHeight - margin - 120, 250, 120);

                // Add address (top center)
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
                float addressX = margin + 200;
                float addressY = pageHeight - margin - 20;
                contentStream.newLineAtOffset(addressX, addressY);
                contentStream.showText("138/4, First Floor,");
                contentStream.newLineAtOffset(0, -14);
                contentStream.showText("(Upstairs ot TVS Mobility)");
                contentStream.newLineAtOffset(0, -14);
                contentStream.showText("Vivekanandar salai, Nasiyanur road,");
                contentStream.newLineAtOffset(0, -14);
//                contentStream.showText("Nasiyanur road,");
//                contentStream.newLineAtOffset(0, -12);
                contentStream.showText("sampath nagar, Erode - 638011");
                contentStream.newLineAtOffset(0, -14);
//                contentStream.showText("Erode - 638011");
//                contentStream.newLineAtOffset(0, -12);
                contentStream.showText("CELL : +91 91507 28104");
                contentStream.newLineAtOffset(0, -14);
                contentStream.showText("EMAIL : gtechspeed@gmail.com");
                contentStream.endText();

                // Add QR Code (top right)
                PDImageXObject qrCode = PDImageXObject.createFromByteArray(document, qrCodeImage, "QR Code");
                contentStream.drawImage(qrCode, pageWidth - margin - 100, pageHeight - margin - 100, 100, 100);

                // Add title
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.beginText();
                String title = "CERTIFICATE OF INSTALLATION OF " + ((BaseForm) form).getEquipmentName().toUpperCase();
                float titleWidth = PDType1Font.TIMES_BOLD.getStringWidth(title) / 1000 * 16;
                contentStream.newLineAtOffset((pageWidth - titleWidth) / 2, pageHeight - margin - 150);
                contentStream.showText(title);
                contentStream.endText();

                // Add main content
                float yPosition = pageHeight - margin - 180;
                float leftMargin = margin;

                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                addParagraph(contentStream, "We GTech Automotives, certify that " + ((BaseForm) form).getModal().toUpperCase() + " has been successfully installed with " + ((BaseForm) form).getEquipmentName().toUpperCase() + ". We hereby affirm that they are competent to install the specified equipment.", leftMargin, yPosition, 14, true);

                yPosition -= 80;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText("MODELS: " + ((BaseForm) form).getModal().toUpperCase());
                contentStream.endText();

                yPosition -= 40;
                addParagraph(contentStream, "The installation was carried out at " + ((BaseForm) form).getRtoOffice().toUpperCase() + " Regional Transport Office on the date mentioned and the named person has been issued with a certificate of competency.", leftMargin, yPosition, 14, true);

                yPosition -= 70;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText("INSTALLATION DETAILS:");
                contentStream.endText();

                yPosition -= 30;
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(leftMargin, yPosition);

                // Installation details
                String[] labels = {"REGISTRATION NO", "MAKE/MODEL", "CHASIS NO", "NAME & ADDRESS"};
                String[] values = {((BaseForm) form).getRegistrationNumber().toUpperCase(), ((BaseForm) form).getMake().toUpperCase(), ((BaseForm) form).getChassisNumber().toUpperCase(), (((BaseForm) form).getName() + ", " + ((BaseForm) form).getAddress()).toUpperCase()};
                float maxLabelWidth = 0;
                for (String label : labels) {
                    float width = PDType1Font.TIMES_ROMAN.getStringWidth(label) / 1000 * 12;
                    if (width > maxLabelWidth) maxLabelWidth = width;
                }
                for (int i = 0; i < labels.length; i++) {
                    contentStream.showText(labels[i]);
                    contentStream.newLineAtOffset(maxLabelWidth + 10, 0);
                    contentStream.showText("   : " + values[i]);
                    contentStream.newLineAtOffset(-(maxLabelWidth + 10), -25.5f);
                }
                contentStream.endText();

                yPosition -= 130;
                addParagraph(contentStream, "We confirm that, the equipment installed " + ((BaseForm) form).getModal().toUpperCase() + " is working in a good condition.", leftMargin, yPosition, 14, false);

                yPosition -= 50;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText("DATE: " + ((BaseForm) form).getInstallationDate().toString().toUpperCase());
                contentStream.endText();

                // Add signature and seal
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(pageWidth - margin - 170, margin + 80);
                contentStream.showText("For GTech Automotives");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Authorized Signatory");
                contentStream.endText();

                // Add seal image
                PDImageXObject sealImage = PDImageXObject.createFromFile("C:\\Users\\hp\\3D Objects\\invoicegen\\src\\main\\java\\com\\project\\invoicegen\\util\\seal.png", document);
                contentStream.drawImage(sealImage, pageWidth - margin - 150, margin + 10, 100, 100);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private byte[] generateCameraPdf(CameraForm form, String baseUrl, String formType) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Generate QR Code
            String pdfUrl = baseUrl + "/api/" + formType + "/pdf/" + form.getId();
            byte[] qrCodeImage = qrCodeGenerator.generateQRCode(pdfUrl, 100, 100);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float pageHeight = page.getMediaBox().getHeight();
                float pageWidth = page.getMediaBox().getWidth();
                float margin = 50;

                // Add logo (top left)
                PDImageXObject logo = PDImageXObject.createFromFile("C:\\Users\\hp\\3D Objects\\invoicegen\\src\\main\\java\\com\\project\\invoicegen\\util\\logo.png", document);
                contentStream.drawImage(logo, margin-30, pageHeight - margin - 120, 250, 120);

                // Add address (top center)
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
                float addressX = margin + 200;
                float addressY = pageHeight - margin - 20;
                contentStream.newLineAtOffset(addressX, addressY);
                contentStream.showText("138/4, First Floor,");
                contentStream.newLineAtOffset(0, -14);
                contentStream.showText("(Upstairs ot TVS Mobility)");
                contentStream.newLineAtOffset(0, -14);
                contentStream.showText("Vivekanandar salai, Nasiyanur road,");
                contentStream.newLineAtOffset(0, -14);
//                contentStream.showText("Nasiyanur road,");
//                contentStream.newLineAtOffset(0, -12);
                contentStream.showText("sampath nagar, Erode - 638011");
                contentStream.newLineAtOffset(0, -14);
//                contentStream.showText("Erode - 638011");
//                contentStream.newLineAtOffset(0, -12);
                contentStream.showText("CELL : +91 91507 28104");
                contentStream.newLineAtOffset(0, -14);
                contentStream.showText("EMAIL : gtechspeed@gmail.com");
                contentStream.endText();

                // Add QR Code (top right)
                PDImageXObject qrCode = PDImageXObject.createFromByteArray(document, qrCodeImage, "QR Code");
                contentStream.drawImage(qrCode, pageWidth - margin - 100, pageHeight - margin - 100, 100, 100);

                // Add title
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.beginText();
                String title = "CERTIFICATE OF INSTALLATION OF " + form.getEquipmentName().toUpperCase();
                float titleWidth = PDType1Font.TIMES_BOLD.getStringWidth(title) / 1000 * 16;
                contentStream.newLineAtOffset((pageWidth - titleWidth) / 2, pageHeight - margin - 150);
                contentStream.showText(title);
                contentStream.endText();

                // Add main content
                float yPosition = pageHeight - margin - 180;
                float leftMargin = margin;

                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                addParagraph(contentStream, "We GTech Automotives, certify that " + form.getModal().toUpperCase() + " has been successfully installed with WI-FI Camera. We hereby affirm that they are competent to install the specified equipment.", leftMargin, yPosition, 14, true);

                yPosition -= 80;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText("MODELS: " + form.getModal().toUpperCase());
                contentStream.endText();

                yPosition -= 40;
                addParagraph(contentStream, "The installation was carried out at " + form.getRtoOffice().toUpperCase() + " Regional Transport Office on the date mentioned and the named person has been issued with a certificate of competency.", leftMargin, yPosition, 14, true);

                yPosition -= 70;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText("INSTALLATION DETAILS:");
                contentStream.endText();

                yPosition -= 30;
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                String[] labels = {"REGISTRATION NO", "MAKE/MODEL", "CHASIS NO", "CAMERA SERIAL NO", "NAME & ADDRESS"};
                String[] values = {form.getRegistrationNumber().toUpperCase(), form.getMake().toUpperCase(), form.getChassisNumber().toUpperCase(), form.getCameraSerialNumber().toUpperCase(), (form.getName() + ", " + form.getAddress()).toUpperCase()};
                float maxLabelWidth = 0;
                for (String label : labels) {
                    float width = PDType1Font.TIMES_ROMAN.getStringWidth(label) / 1000 * 12;
                    if (width > maxLabelWidth) maxLabelWidth = width;
                }
                for (int i = 0; i < labels.length; i++) {
                    contentStream.showText(labels[i]);
                    contentStream.newLineAtOffset(maxLabelWidth + 10, 0);
                    contentStream.showText("   : " + values[i]);
                    contentStream.newLineAtOffset(-(maxLabelWidth + 10), -25.5f);
                }
                contentStream.endText();

                yPosition -= 150;
                addParagraph(contentStream, "We confirm that, the equipment installed " + form.getModal().toUpperCase() + " is working in a good condition.", leftMargin, yPosition, 14, false);

                yPosition -= 50;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText("DATE: " + form.getInstallationDate().toString().toUpperCase());
                contentStream.endText();

                // Add signature and seal
                contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(pageWidth - margin - 170, margin + 80);
                contentStream.showText("For GTech Automotives");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Authorized Signatory");
                contentStream.endText();

                // Add seal image
                PDImageXObject sealImage = PDImageXObject.createFromFile("C:\\Users\\hp\\3D Objects\\invoicegen\\src\\main\\java\\com\\project\\invoicegen\\util\\seal.png", document);
                contentStream.drawImage(sealImage, pageWidth - margin - 150, margin + 10, 100, 100);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addParagraph(PDPageContentStream contentStream, String text, float x, float y, float fontSize, boolean indent) throws IOException {
        List<String> lines = new ArrayList<>();
        int lastSpace = -1;
        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0)
                spaceIndex = text.length();
            String subString = text.substring(0, spaceIndex);
            float size = fontSize * PDType1Font.TIMES_ROMAN.getStringWidth(subString) / 1000;
            if (size > 500) {
                if (lastSpace < 0)
                    lastSpace = spaceIndex;
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();
                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                lines.add(text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, fontSize);
        contentStream.newLineAtOffset(x, y);
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0 && indent) {
                contentStream.newLineAtOffset(20, 0);  // Indent first line
                contentStream.showText(lines.get(i));
                contentStream.newLineAtOffset(-20, -1.5f * fontSize);
            } else {
                contentStream.showText(lines.get(i));
                contentStream.newLineAtOffset(0, -1.5f * fontSize);
            }
        }
        contentStream.endText();
    }
}

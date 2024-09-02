package com.project.invoicegen.controller;

import com.project.invoicegen.model.CameraForm;
import com.project.invoicegen.service.CameraFormService;
import com.project.invoicegen.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/camera/pdf")
public class CameraPdfController {
    @Autowired
    private CameraFormService cameraFormService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/{formId}")
    public ResponseEntity<byte[]> getPdf(@PathVariable Long formId, UriComponentsBuilder uriBuilder) {
        CameraForm cameraForm = cameraFormService.getCameraFormById(formId);
        if (cameraForm== null) {
            return ResponseEntity.notFound().build();
        }

        String baseUrl = uriBuilder.build().toUriString();
        byte[] pdfBytes = pdfService.generatePdfForCameraForm(cameraForm, baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "form.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}

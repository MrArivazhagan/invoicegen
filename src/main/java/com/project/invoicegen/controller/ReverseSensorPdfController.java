package com.project.invoicegen.controller;

import com.project.invoicegen.model.ReverseSensorForm;
import com.project.invoicegen.service.PdfService;
import com.project.invoicegen.service.ReverseSensorFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/reverse-sensor/pdf")
public class ReverseSensorPdfController {
    @Autowired
    private ReverseSensorFormService reverseSensorFormService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/{formId}")
    public ResponseEntity<byte[]> getPdf(@PathVariable Long formId, UriComponentsBuilder uriBuilder) {
        ReverseSensorForm reverseSensorForm = reverseSensorFormService.getReverseSensorFormById(formId);
        if (reverseSensorForm== null) {
            return ResponseEntity.notFound().build();
        }

        String baseUrl = uriBuilder.build().toUriString();
        byte[] pdfBytes = pdfService.generatePdfForReverseSensorForm(reverseSensorForm, baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "form.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}

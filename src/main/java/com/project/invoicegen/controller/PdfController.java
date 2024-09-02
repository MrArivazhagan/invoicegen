//package com.project.invoicegen.controller;
//
//import com.project.invoicegen.model.Form;
//import com.project.invoicegen.service.FormService;
//import com.project.invoicegen.service.PdfService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@RestController
//@RequestMapping("/api/pdf")
//public class PdfController {
//    @Autowired
//    private FormService formService;
//
//    @Autowired
//    private PdfService pdfService;
//
//    @GetMapping("/{formId}")
//    public ResponseEntity<byte[]> getPdf(@PathVariable Long formId, UriComponentsBuilder uriBuilder) {
//        Form form = formService.getFormById(formId);
//        if (form == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        String baseUrl = uriBuilder.build().toUriString();
//        byte[] pdfBytes = pdfService.generatePdf(form, baseUrl);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("filename", "form.pdf");
//
//        return ResponseEntity.ok().headers(headers).body(pdfBytes);
//    }
//}

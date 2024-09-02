//package com.project.invoicegen.controller;
//
//import com.project.invoicegen.model.Form;
//import com.project.invoicegen.service.FormService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/forms")
//public class FormController {
//
//    @Autowired
//    private FormService formService;
//
//    @PostMapping("/")
//    public ResponseEntity<?> createForm(@RequestBody Form form) {
//        System.out.println(form);
//        formService.saveForm(form);
//        return ResponseEntity.ok("Form created successfully");
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<Form>> getUserForms(@PathVariable Long userId) {
//        return ResponseEntity.ok(formService.getFormsByUserId(userId));
//    }
//
//    @GetMapping("/form/{formId}")
//    public ResponseEntity<Form> getForm(@PathVariable Long formId) {
//        return ResponseEntity.ok(formService.getForm(formId));
//    }
//
//    @PutMapping("/{formId}")
//    public ResponseEntity<?> updateForm(@PathVariable Long formId, @RequestBody Form form) {
//        formService.updateForm(formId, form);
//        return ResponseEntity.ok("Form updated successfully");
//    }
//
//    @DeleteMapping("/{formId}")
//    public ResponseEntity<?> deleteForm(@PathVariable Long formId) {
//        formService.deleteForm(formId);
//        return ResponseEntity.ok("Form deleted successfully");
//    }
//}

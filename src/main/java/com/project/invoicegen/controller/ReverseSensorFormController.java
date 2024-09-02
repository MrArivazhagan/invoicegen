package com.project.invoicegen.controller;

import com.project.invoicegen.model.ReverseSensorForm;
import com.project.invoicegen.service.ReverseSensorFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reverse-sensor-forms")
public class ReverseSensorFormController {

    @Autowired
    private ReverseSensorFormService reverseSensorFormService;

    @PostMapping("/")
    public ResponseEntity<?> createReverseSensorForm(@RequestBody ReverseSensorForm reverseSensorForm) {
        reverseSensorFormService.saveReverseSensorForm(reverseSensorForm);
        return ResponseEntity.ok("Reverse Sensor Form created successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReverseSensorForm>> getUserReverseSensorForms(@PathVariable Long userId) {
        return ResponseEntity.ok(reverseSensorFormService.getReverseSensorFormsByUserId(userId));
    }

    @GetMapping("/form/{formId}")
    public ResponseEntity<ReverseSensorForm> getReverseSensorForm(@PathVariable Long formId) {
        return ResponseEntity.ok(reverseSensorFormService.getReverseSensorForm(formId));
    }

    @PutMapping("/{formId}")
    public ResponseEntity<?> updateReverseSensorForm(@PathVariable Long formId, @RequestBody ReverseSensorForm form) {
        reverseSensorFormService.updateReverseSensorForm(formId, form);
        return ResponseEntity.ok("Reverse Sensor Form updated successfully");
    }

    @DeleteMapping("/{formId}")
    public ResponseEntity<?> deleteReverseSensorForm(@PathVariable Long formId) {
        reverseSensorFormService.deleteReverseSensorForm(formId);
        return ResponseEntity.ok("Reverse Sensor Form deleted successfully");
    }
}

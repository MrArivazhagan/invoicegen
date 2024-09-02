package com.project.invoicegen.controller;

import com.project.invoicegen.model.CameraForm;
import com.project.invoicegen.service.CameraFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camera-forms")
public class CameraFormController {

    @Autowired
    private CameraFormService cameraFormService;

    @PostMapping("/")
    public ResponseEntity<?> createCameraForm(@RequestBody CameraForm cameraForm) {
        cameraFormService.saveCameraForm(cameraForm);
        return ResponseEntity.ok("Camera Form created successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CameraForm>> getUserCameraForms(@PathVariable Long userId) {
        return ResponseEntity.ok(cameraFormService.getCameraFormsByUserId(userId));
    }

    @GetMapping("/form/{formId}")
    public ResponseEntity<CameraForm> getCameraForm(@PathVariable Long formId) {
        return ResponseEntity.ok(cameraFormService.getCameraForm(formId));
    }

    @PutMapping("/{formId}")
    public ResponseEntity<?> updateCameraForm(@PathVariable Long formId, @RequestBody CameraForm form) {
        cameraFormService.updateCameraForm(formId, form);
        return ResponseEntity.ok("Camera Form updated successfully");
    }

    @DeleteMapping("/{formId}")
    public ResponseEntity<?> deleteCameraForm(@PathVariable Long formId) {
        cameraFormService.deleteCameraForm(formId);
        return ResponseEntity.ok("Camera Form deleted successfully");
    }
}

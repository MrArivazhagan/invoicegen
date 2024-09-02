package com.project.invoicegen.service;

import com.project.invoicegen.model.CameraForm;
import com.project.invoicegen.model.User;
import com.project.invoicegen.repository.CameraFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraFormService {

    @Autowired
    private CameraFormRepository cameraFormRepository;

    @Autowired
    private UserService userService;

    public void saveCameraForm(CameraForm cameraForm) {
        cameraForm.setUser(userService.getCurrentUser());
        cameraFormRepository.save(cameraForm);
    }

    public List<CameraForm> getCameraFormsByUserId(Long userId) {
        User currentUser = userService.getCurrentUser();
        if (!currentUser.getId().equals(userId)) {
            throw new SecurityException("Access denied");
        }
        return cameraFormRepository.findByUserId(userId);
    }

    public CameraForm getCameraForm(Long formId) {
        CameraForm cameraForm =  cameraFormRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Camera Form not found"));
        if (!cameraForm.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new SecurityException("Access denied");
        }
        return cameraForm;
    }

    public CameraForm getCameraFormById(Long formId) {

        return cameraFormRepository.findById(formId).orElse(null);
    }

    public void updateCameraForm(Long formId, CameraForm form) {
        CameraForm existingForm = cameraFormRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Camera Form not found"));
        if (!existingForm.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new SecurityException("Access denied");
        }
        form.setId(formId);
        form.setUser(existingForm.getUser());
        cameraFormRepository.save(form);
    }

    public void deleteCameraForm(Long formId) {
        CameraForm cameraForm = cameraFormRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
        if (!cameraForm.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new SecurityException("Access denied");
        }
        cameraFormRepository.deleteById(formId);
    }
}

package com.project.invoicegen.service;

import com.project.invoicegen.model.CameraForm;
import com.project.invoicegen.model.ReverseSensorForm;
import com.project.invoicegen.model.User;
import com.project.invoicegen.repository.ReverseSensorFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReverseSensorFormService {

    @Autowired
    private ReverseSensorFormRepository reverseSensorFormRepository;

    @Autowired
    private UserService userService;
    public void saveReverseSensorForm(ReverseSensorForm reverseSensorForm) {
        reverseSensorForm.setUser(userService.getCurrentUser());
        reverseSensorFormRepository.save(reverseSensorForm);
    }

    public List<ReverseSensorForm> getReverseSensorFormsByUserId(Long userId) {
        User currentUser = userService.getCurrentUser();
        if (!currentUser.getId().equals(userId)) {
            throw new SecurityException("Access denied");
        }
        return reverseSensorFormRepository.findByUserId(userId);
    }

    public ReverseSensorForm getReverseSensorForm(Long formId) {
        ReverseSensorForm reverseSensorForm =  reverseSensorFormRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Reverse Sensor Form not found"));
        if (!reverseSensorForm.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new SecurityException("Access denied");
        }
        return reverseSensorForm;
    }

    public ReverseSensorForm getReverseSensorFormById(Long formId) {
        return reverseSensorFormRepository.findById(formId).orElse(null);
    }
    public void updateReverseSensorForm(Long formId, ReverseSensorForm form) {
        ReverseSensorForm existingForm = reverseSensorFormRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Reverse Sensor Form not found"));
        if (!existingForm.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new SecurityException("Access denied");
        }
        form.setId(formId);
        form.setUser(existingForm.getUser());
        reverseSensorFormRepository.save(form);
    }

    public void deleteReverseSensorForm(Long formId) {
        ReverseSensorForm reverseSensorForm = reverseSensorFormRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
        if (!reverseSensorForm.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new SecurityException("Access denied");
        }
        reverseSensorFormRepository.deleteById(formId);
    }
}

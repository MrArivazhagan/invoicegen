//package com.project.invoicegen.service;
//
//import com.project.invoicegen.model.Form;
//import com.project.invoicegen.model.User;
//import com.project.invoicegen.repository.FormRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FormService {
//
//    @Autowired
//    private FormRepository formRepository;
//
//    @Autowired
//    private UserService userService;
//
//    public void saveForm(Form form) {
//        System.out.println(userService.getCurrentUser());
//        form.setUser(userService.getCurrentUser());
//        formRepository.save(form);
//    }
//
//    public List<Form> getFormsByUserId(Long userId) {
//        User currentUser = userService.getCurrentUser();
//        if (!currentUser.getId().equals(userId)) {
//            throw new SecurityException("Access denied");
//        }
//        return formRepository.findByUserId(userId);
//    }
//
//    public Form getForm(Long formId) {
//        Form form = formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
//        if (!form.getUser().getId().equals(userService.getCurrentUser().getId())) {
//            throw new SecurityException("Access denied");
//        }
//        return form;
//    }
//
//    public Form getFormById(Long formId) {
//        return formRepository.findById(formId).orElse(null);
//    }
//
//    public void updateForm(Long formId, Form form) {
//        Form existingForm = formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
//        if (!existingForm.getUser().getId().equals(userService.getCurrentUser().getId())) {
//            throw new SecurityException("Access denied");
//        }
//        form.setId(formId);
//        form.setUser(existingForm.getUser());
//        formRepository.save(form);
//    }
//
//    public void deleteForm(Long formId) {
//        Form form = formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
//        if (!form.getUser().getId().equals(userService.getCurrentUser().getId())) {
//            throw new SecurityException("Access denied");
//        }
//        formRepository.deleteById(formId);
//    }
//}

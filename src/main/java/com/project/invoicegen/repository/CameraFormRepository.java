package com.project.invoicegen.repository;

import com.project.invoicegen.model.CameraForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CameraFormRepository extends JpaRepository<CameraForm, Long> {
    List<CameraForm> findByUserId(Long userId);
}

package com.project.invoicegen.repository;

import com.project.invoicegen.model.ReverseSensorForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReverseSensorFormRepository extends JpaRepository<ReverseSensorForm, Long> {
    List<ReverseSensorForm> findByUserId(Long userId);
}

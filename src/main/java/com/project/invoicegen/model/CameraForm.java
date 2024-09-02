package com.project.invoicegen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "camera_forms")
public class CameraForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String deviceName;
    private String equipmentName;
    private String modal;
    private String rtoOffice;
    private String registrationNumber;
    private String make;
    private String chassisNumber;
    private String name;
    private String address;
    private LocalDate installationDate;
    private String cameraSerialNumber;
}

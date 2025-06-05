package org.example.healthcare.medicalrecord;//package com.example.healthcaresystem.medicalrecord;
//import com.example.healthcaresystem.patient.Patient;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.PastOrPresent;
//import jakarta.validation.constraints.Size;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "medical_records")
//public class MedicalRecord {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "patient_id", nullable = false)
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "doctor_id", nullable = false)
//    private Doctor doctor;

//    @NotNull
//    @Size(max = 255)
//    private String diagnosis;
//
//    @Size(max = 500)
//    private String prescription;
//
//    @NotNull
//    @PastOrPresent(message = "Record date must be in the past or present")
//    private LocalDateTime recordDate;
//
//}

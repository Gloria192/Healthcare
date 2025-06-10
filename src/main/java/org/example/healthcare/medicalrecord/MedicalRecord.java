package org.example.healthcare.medicalrecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.healthcare.doctor.Doctor;
import org.example.healthcare.patient.Patient;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @NotBlank(message = "Patient is required")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotBlank(message = "Doctor is required")
    private Doctor doctor;

    @Column(nullable = false)
    @NotBlank(message = "Diagnosis is required")
    @Size(max = 255, message = "Diagnosis cannot exceed 255 characters")
    private String diagnosis;

    @Size(max = 500, message = "Prescription cannot exceed 500 characters")
    private String prescription;

    @Column(name = "record_date", nullable = false)
    @PastOrPresent(message = "Record date cannot be in the future")
    private LocalDateTime recordDate;
}
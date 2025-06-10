package org.example.healthcare.appointment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.healthcare.doctor.Doctor;
import org.example.healthcare.patient.Patient;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"patient_id", "doctor_id", "appointment_date"})
})
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "Doctor is required")
    private Doctor doctor;

    @Column(name = "appointment_date", nullable = false)
    @NotNull(message = "Appointment date is required")
    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Status is required")
    private AppointmentStatus status;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;

     enum AppointmentStatus {
        SCHEDULED, COMPLETED, CANCELLED
    }
}

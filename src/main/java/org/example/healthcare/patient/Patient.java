package org.example.healthcare.patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.appointment.Appointment;
import org.example.healthcare.clinic.Clinic;
import org.example.healthcare.medicalrecord.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column ( length = 50, nullable = false)
    private String firstName;
    @Column ( length = 50, nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    @Email
    private String email;
    @Column ( length = 20, nullable = false)
    @Pattern(regexp = "^[0-9]+$", message = "Phone must contain only numbers")
    private String phone;
    @Column (  nullable = false)
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;


    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecord> medicalRecords;


}


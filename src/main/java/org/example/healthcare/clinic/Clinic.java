package org.example.healthcare.clinic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.doctor.Doctor;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="clinics")
public class Clinic {

        @Id @GeneratedValue private Long id;
        @Column(unique = true, length = 100)
        private String name;
        @Column(length = 255)
        private String address;
        @Column(unique = true, length = 20)
        private String phone;

        @OneToMany(mappedBy = "clinic") private List<Patient> patients;
        @OneToMany(mappedBy = "clinic") private List<Doctor> doctors;
    }

package org.example.healthcare.patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PatientRepository extends JpaRepository<Patient , Long> {

}
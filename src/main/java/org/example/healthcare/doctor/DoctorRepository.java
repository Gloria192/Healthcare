package org.example.healthcare.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  DoctorRepository extends JpaRepository<Doctor, Long> {

}

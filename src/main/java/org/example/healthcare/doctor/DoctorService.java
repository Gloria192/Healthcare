package org.example.healthcare.doctor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository DoctorRepository;
    public DoctorService(DoctorRepository DoctorRepository) {
        this.DoctorRepository = DoctorRepository;
    }
   Doctor createDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setSpecialty(doctorDto.getSpecialty());
        return DoctorRepository.save(doctor);
   }

    public List<Doctor > getAllDoctors() {
        return DoctorRepository.findAll();
    }
    public Doctor  getDoctorById(Long id) {
        return DoctorRepository .findById(id)
                .orElseThrow(() -> new RuntimeException("doctor Not Found"));
    }
    public Doctor updateDoctor(Long id, DoctorDto doctorDto) {
        Doctor existingDoctor = DoctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existingDoctor.setFirstName(doctorDto.getFirstName());
        existingDoctor.setLastName(doctorDto.getLastName());
        existingDoctor.setEmail(doctorDto.getEmail());
        existingDoctor.setSpecialty(doctorDto.getSpecialty());

        return DoctorRepository.save(existingDoctor);
    }
     public Doctor deleteDoctor(Long id) {
        DoctorRepository.deleteById(id);
return null ;
 }

}

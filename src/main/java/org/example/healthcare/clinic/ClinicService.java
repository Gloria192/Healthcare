package org.example.healthcare.clinic;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    private  final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }


    public Clinic getClinicById(Long id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinic not found with ID: " + id));
    }


    public Clinic updateClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }
    public Clinic deleteClinicById(Long id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinic not found with ID: " + id));

        clinicRepository.delete(clinic);

        return clinic;
    }
    public Clinic createClinic(Clinic clinic) {

        return clinicRepository.save(clinic);

    }
}

package org.example.healthcare.patient;
import org.example.healthcare.clinic.Clinic;
import org.example.healthcare.clinic.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    private ClinicRepository clinicRepository;

    public Patient createPatient(PatientDto patientDto) {
        Clinic clinic = clinicRepository.findById(patientDto.getClinicId())
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setEmail(patientDto.getEmail());
        patient.setPhone(patientDto.getPhone());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setClinic(clinic);

        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }
    public boolean updatePatientIfExists(Long id, PatientDto patientDto) {
        if (patientRepository.existsById(id)) {
            Patient patient = patientRepository.findById(id).get();
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setEmail(patientDto.getEmail());
            patientRepository.save(patient);
            return true;
        }
        return false;
    }
    public Patient updatePatient(Long id, PatientDto patientDto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        existingPatient.setFirstName(patientDto.getFirstName());
        existingPatient.setLastName(patientDto.getLastName());
        existingPatient.setEmail(patientDto.getEmail());

        return patientRepository.save(existingPatient);
    }
    public void deletePatientById(Long id) {

        patientRepository.deleteById(id);
    }

}


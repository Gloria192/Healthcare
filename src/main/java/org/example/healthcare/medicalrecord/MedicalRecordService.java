package org.example.healthcare.medicalrecord;
import jakarta.validation.Valid;
import org.example.healthcare.doctor.DoctorRepository;
import org.example.healthcare.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public MedicalRecord createMedicalRecord(@Valid MedicalRecord medicalRecord) {
        validatePatientAndDoctor(medicalRecord);
        if (medicalRecord.getRecordDate() == null) {
            medicalRecord.setRecordDate(LocalDateTime.now());
        }
        return medicalRecordRepository.save(medicalRecord);
    }

    public List<MedicalRecord> getAllMedicalRecords(Long patientId, Long doctorId) {
        if (patientId != null) {
            return medicalRecordRepository.findByPatientId(patientId);
        } else if (doctorId != null) {
            return medicalRecordRepository.findByDoctorId(doctorId);
        }
        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public MedicalRecord updateMedicalRecord(Long id, @Valid MedicalRecord medicalRecordDetails) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));
        validatePatientAndDoctor(medicalRecordDetails);
        medicalRecord.setPatient(medicalRecordDetails.getPatient());
        medicalRecord.setDoctor(medicalRecordDetails.getDoctor());
        medicalRecord.setDiagnosis(medicalRecordDetails.getDiagnosis());
        medicalRecord.setPrescription(medicalRecordDetails.getPrescription());
        medicalRecord.setRecordDate(medicalRecordDetails.getRecordDate());
        return medicalRecordRepository.save(medicalRecord);
    }

    public void deleteMedicalRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new RuntimeException("Medical record not found with id: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }

    private void validatePatientAndDoctor(MedicalRecord medicalRecord) {
        if (!patientRepository.existsById(medicalRecord.getPatient().getId())) {
            throw new RuntimeException("Patient not found with id: " + medicalRecord.getPatient().getId());
        }
        if (!doctorRepository.existsById(medicalRecord.getDoctor().getId())) {
            throw new RuntimeException("Doctor not found with id: " + medicalRecord.getDoctor().getId());
        }
    }
}

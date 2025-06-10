package org.example.healthcare.medicalrecord;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord created = medicalRecordService.createMedicalRecord(medicalRecord);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getMedicalRecords(
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) Long doctorId) {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords(patientId, doctorId);
        return ResponseEntity.ok(medicalRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return medicalRecordService.getMedicalRecordById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id, @Valid @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord updated = medicalRecordService.updateMedicalRecord(id, medicalRecord);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
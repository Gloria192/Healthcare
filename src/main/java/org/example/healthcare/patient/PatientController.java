package org.example.healthcare.patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")  // Base path for all patient routes
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient createPatient(@RequestBody PatientDto patientDto) {
        return patientService.createPatient(patientDto);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        patientService.deletePatientById(id);
    }
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto) {
        return patientService.updatePatient(id, patientDto);
    }
    @PutMapping("/{id}/update-if-exists")
    public boolean updatePatientIfExists(@PathVariable Long id, @RequestBody PatientDto patientDto) {
        return patientService.updatePatientIfExists(id, patientDto);
    }

}


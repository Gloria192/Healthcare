package org.example.healthcare.clinic;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clinics")
public class ClinicController {
    private final ClinicService clinicService;


    @PutMapping("api/clinic/{id}")
    @Operation(summary="updates all clinics")
    public Clinic updateClinic(@PathVariable Long id, @RequestBody ClinicCreateDto clinicCreateDto) {
        Clinic clinic = clinicService.getClinicById(id);
        clinic.setName(clinicCreateDto.getName());
        clinic.setAddress(clinicCreateDto.getAddress());
        clinic.setPhone(clinicCreateDto.getPhone());

        return clinicService.updateClinic(clinic);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get clinic by Id")
    public Clinic getClinicById(@PathVariable Long id) {
        return clinicService.getClinicById(id); // Calls service correctly
    }
    @GetMapping("api/clinics")
    @Operation
            (summary="Get all clinics")
    public List<Clinic> getAllClinics() {
        return clinicService.getAllClinics();
    }
    @DeleteMapping
    @Operation(summary = "delete clinic(if no patients and doctors)")

            public void deleteClinicById(@RequestParam Long id,@RequestParam ClinicCreateDto clinicCreateDto ) {
        clinicService.deleteClinicById(id);
        Clinic clinic = clinicService.getClinicById(id);
        clinic.setName(clinicCreateDto.getName());
        clinic.setAddress(clinicCreateDto.getAddress());
        clinic.setPhone(clinicCreateDto.getPhone());
        clinicService.deleteClinicById(id);
    }
    @PostMapping
    @Operation(summary = "the clinics added")
    public Clinic createClinic(@RequestBody ClinicCreateDto clinicCreateDto) {
        Clinic clinic = new Clinic();
        clinic.setName(clinicCreateDto.getName());
        clinic.setAddress(clinicCreateDto.getAddress());
        clinic.setPhone(clinicCreateDto.getPhone());
        return clinicService.createClinic(clinic);
    }


    }




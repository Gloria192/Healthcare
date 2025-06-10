package org.example.healthcare.appointment;
import jakarta.validation.Valid;
import org.example.healthcare.doctor.DoctorRepository;
import org.example.healthcare.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Appointment createAppointment(@Valid Appointment appointment) {
        validatePatientAndDoctor(appointment);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments(Long patientId, Long doctorId, LocalDate date, Pageable pageable) {
        if (patientId != null) {
            return appointmentRepository.findByPatientId(patientId);
        } else if (doctorId != null) {
            return appointmentRepository.findByDoctorId(doctorId);
        } else if (date != null) {
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(23, 59, 59);
            return appointmentRepository.findByAppointmentDateBetween(start, end);
        }
        return appointmentRepository.findAll(pageable).getContent();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment updateAppointment(Long id, @Valid Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        validatePatientAndDoctor(appointmentDetails);
        appointment.setPatient(appointmentDetails.getPatient());
        appointment.setDoctor(appointmentDetails.getDoctor());
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setStatus(appointmentDetails.getStatus());
        appointment.setNotes(appointmentDetails.getNotes());
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    private void validatePatientAndDoctor(Appointment appointment) {
        if (!patientRepository.existsById(appointment.getPatient().getId())) {
            throw new RuntimeException("Patient not found with id: " + appointment.getPatient().getId());
        }
        if (!doctorRepository.existsById(appointment.getDoctor().getId())) {
            throw new RuntimeException("Doctor not found with id: " + appointment.getDoctor().getId());
        }
    }
}
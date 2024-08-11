package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Appointment;
import org.example.clinicservice.entity.enums.Status;
import org.example.clinicservice.repository.AppointmentRepository;
import org.example.clinicservice.service.interfeces.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAppointmentsBySpecialistId(UUID specialistId) {
        return appointmentRepository.findBySpecialistId(specialistId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(UUID patientId) {

        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByStatus(Status status) {

        return appointmentRepository.findByStatus(status);
    }

    @Override
    public List<Appointment> getAppointmentsByDateTime(LocalDateTime dateTime) {

        return appointmentRepository.findByDateTime(dateTime);
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Appointment;
import org.example.clinicservice.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    List<Appointment> getAppointmentsBySpecialistId(UUID specialistId);
    List<Appointment> getAppointmentsByPatientId(UUID patientId);
    List<Appointment> getAppointmentsByStatus(Status status);
    List<Appointment> getAppointmentsByDateTime(LocalDateTime dateTime);
    void saveAppointment(Appointment appointment);
    void deleteAppointment(UUID appointmentId);
}
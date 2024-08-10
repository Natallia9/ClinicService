package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Appointment;
import org.example.clinicservice.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findBySpecialistId(UUID specialistId);
    List<Appointment> findByPatientId(UUID patientId);
    List<Appointment> findByStatus(Status status);
    List<Appointment> findByDateTime(LocalDateTime dateTime);
}

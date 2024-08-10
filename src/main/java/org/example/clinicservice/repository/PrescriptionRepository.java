package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

    List<Prescription> findByPatientId(UUID patientId);
    List<Prescription> findByDoctorId(UUID doctorId);
    List<Prescription> findByMedicationNameContaining(String medicationName);
    List<Prescription> findByPrescriptionDate(LocalDateTime prescriptionDate);
    List<Prescription> findByPrescriptionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}



package org.example.clinicservice.repository;

import org.example.clinicservice.entity.PatientVisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PatientVisitHistoryRepository extends JpaRepository<PatientVisitHistory, UUID> {

    PatientVisitHistory findByVisitId(UUID visitId);
    List<PatientVisitHistory> findByPatientId(UUID patientId);
    List<PatientVisitHistory> findBySpecialistId(UUID specialistId);
    List<PatientVisitHistory> findByVisitType(String visitType);
    List<PatientVisitHistory> findByVisitDateTime(LocalDateTime visitDateTime);
    List<PatientVisitHistory> findByVisitDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<PatientVisitHistory> findByPurposeContaining(String purpose);
    List<PatientVisitHistory> findByMedicalRecordId(UUID medicalRecordId);
}

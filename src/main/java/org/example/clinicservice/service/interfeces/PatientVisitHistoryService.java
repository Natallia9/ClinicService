package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.PatientVisitHistory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PatientVisitHistoryService {

    PatientVisitHistory getVisitHistoryById(UUID visitId);
    List<PatientVisitHistory> getVisitHistoryByPatientId(UUID patientId);
    List<PatientVisitHistory> getVisitHistoryBySpecialistId(UUID specialistId);
    List<PatientVisitHistory> getVisitHistoryByVisitType(String visitType);
    List<PatientVisitHistory> getVisitHistoryByVisitDateTime(LocalDateTime visitDateTime);
    List<PatientVisitHistory> getVisitHistoryBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<PatientVisitHistory> searchVisitHistoryByPurpose(String purpose);
    List<PatientVisitHistory> getVisitHistoryByMedicalRecordId(UUID medicalRecordId);
    void saveVisitHistory(PatientVisitHistory visitHistory);
    void deleteVisitHistory(UUID visitId);
}

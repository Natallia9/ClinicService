package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.example.clinicservice.entity.Specialist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface PatientVisitHistoryService {

    PatientVisitHistory getVisitHistoryById(UUID visitId);
    Map<Optional<Patient>, List<PatientVisitHistory>> getVisitHistoryByPatient(UUID patientId);
    Map<Optional<Specialist>, List<PatientVisitHistory>> getVisitHistoryBySpecialist(UUID specialistId);
    Map<Patient, List<PatientVisitHistory>> getVisitHistoryByVisitDateTime(LocalDateTime visitDateTime);
    void saveVisitHistory(PatientVisitHistory visitHistory);
    void deleteVisitHistory(UUID visitId);
}

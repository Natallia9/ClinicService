package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.example.clinicservice.repository.PatientVisitHistoryRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.PatientVisitHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientVisitHistoryServiceImpl implements PatientVisitHistoryService {

    private final PatientVisitHistoryRepository patientVisitHistoryRepository;

    @Override
    public PatientVisitHistory getVisitHistoryById(UUID visitId) {

        return patientVisitHistoryRepository.findByVisitId(visitId);
    }

    @Override
    public List<PatientVisitHistory> getVisitHistoryByPatientId(UUID patientId) {

        return patientVisitHistoryRepository.findByPatientId(patientId);
    }

    @Override
    public List<PatientVisitHistory> getVisitHistoryBySpecialistId(UUID specialistId) {

        return patientVisitHistoryRepository.findBySpecialistId(specialistId);
    }

    @Override
    public List<PatientVisitHistory> getVisitHistoryByVisitType(String visitType) {

        return patientVisitHistoryRepository.findByVisitType(visitType);
    }

    @Override
    public List<PatientVisitHistory> getVisitHistoryByVisitDateTime(LocalDateTime visitDateTime) {

        return patientVisitHistoryRepository.findByVisitDateTime(visitDateTime);
    }

    @Override
    public List<PatientVisitHistory> getVisitHistoryBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        return patientVisitHistoryRepository.findByVisitDateTimeBetween(startDateTime, endDateTime);
    }

    @Override
    public List<PatientVisitHistory> searchVisitHistoryByPurpose(String purpose) {

        return patientVisitHistoryRepository.findByPurposeContaining(purpose);
    }

    @Override
    public List<PatientVisitHistory> getVisitHistoryByMedicalRecordId(UUID medicalRecordId) {

        return patientVisitHistoryRepository.findByMedicalRecordId(medicalRecordId);
    }

    @Override
    public void saveVisitHistory(PatientVisitHistory visitHistory) {
        patientVisitHistoryRepository.save(visitHistory);
    }

    @Override
    public void deleteVisitHistory(UUID visitId) {
        patientVisitHistoryRepository.deleteById(visitId);
    }
}

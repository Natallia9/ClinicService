package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.patientExceptions.PatientNotFoundException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecialistNotFoundException;
import org.example.clinicservice.exceptions.visitHistoryExceptions.VisitHistoryAlreadyExistsException;
import org.example.clinicservice.exceptions.visitHistoryExceptions.VisitHistoryNotFoundException;
import org.example.clinicservice.exceptions.visitHistoryExceptions.VisitHistorySaveException;
import org.example.clinicservice.repository.PatientRepository;
import org.example.clinicservice.repository.PatientVisitHistoryRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.PatientVisitHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientVisitHistoryServiceImpl implements PatientVisitHistoryService {

    private final PatientVisitHistoryRepository patientVisitHistoryRepository;
    private final PatientRepository patientRepository;
    private final SpecialistRepository specialistRepository;

    @Override
    public PatientVisitHistory getVisitHistoryById(UUID visitId) {

        try {
            PatientVisitHistory patientVisitHistory = patientVisitHistoryRepository.findByVisitId(visitId);
            if(visitId == null){
                throw new VisitHistoryNotFoundException(ErrorMessage.PATIENT_VISIT_HISTORY_NOT_FOUND);
            }
            return patientVisitHistory;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving visit history with id " + visitId, e);
        }
    }

    @Override
    public Map<Patient, List<PatientVisitHistory>> getVisitHistoryByPatient(UUID patientId) {

        List<PatientVisitHistory> visitHistory = patientVisitHistoryRepository.findByPatientId(patientId);

        if (visitHistory.isEmpty()) {
            throw new VisitHistoryNotFoundException(ErrorMessage.VISIT_HISTORY_NOT_FOUND);
        }

        try {
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new PatientNotFoundException(ErrorMessage.PATIENT_NOT_FOUND));


            return Collections.singletonMap(patient, visitHistory);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve visit history for patient ID: " + patientId, e);
        }
    }


    @Override
    public Map<Specialist, List<PatientVisitHistory>> getVisitHistoryBySpecialist(UUID specialistId) {

        try {
            Specialist specialist = specialistRepository.findById(specialistId)
                    .orElseThrow(() -> new SpecialistNotFoundException(ErrorMessage.SPECIALIST_NOT_FOUND));

            List<PatientVisitHistory> visitHistory = patientVisitHistoryRepository.findBySpecialistId(specialistId);
            if (visitHistory.isEmpty()) {
                throw new VisitHistoryNotFoundException(ErrorMessage.VISIT_HISTORY_NOT_FOUND);
            }

            return Collections.singletonMap(specialist, visitHistory);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve visit history for specialist ID: " + specialistId, e);
        }
    }

    @Override
    public Map<Patient, List<PatientVisitHistory>> getVisitHistoryByVisitDateTime(LocalDateTime visitDateTime) {

        try {
            List<PatientVisitHistory> visitHistory = patientVisitHistoryRepository.findByVisitDateTime(visitDateTime);
            if (visitHistory.isEmpty()) {
                throw new VisitHistoryNotFoundException(ErrorMessage.VISIT_HISTORY_NOT_FOUND_FOR_DATETIME);
            }

            return visitHistory.stream()
                    .collect(Collectors.groupingBy(PatientVisitHistory::getPatient));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve visit history for date/time: " + visitDateTime, e);
        }
    }

    @Override
    public void saveVisitHistory(PatientVisitHistory visitHistory) {

        if (patientVisitHistoryRepository.existsById(visitHistory.getVisitId())) {
            throw new VisitHistoryAlreadyExistsException(ErrorMessage.PATIENT_VISIT_HISTORY_ALREADY_EXISTS);
        }
        try {
            patientVisitHistoryRepository.save(visitHistory);
        } catch (Exception e) {
            throw new VisitHistorySaveException("Failed to save visit history due to an unexpected error: " + e.getMessage());
        }
    }

    @Override
    public void deleteVisitHistory(UUID visitId) {

        if (!patientVisitHistoryRepository.existsById(visitId)) {
            throw new VisitHistoryNotFoundException(ErrorMessage.PATIENT_VISIT_HISTORY_NOT_FOUND);
        }

        try {
            patientVisitHistoryRepository.deleteById(visitId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete visit history with ID: " + visitId, e);
        }
    }
}


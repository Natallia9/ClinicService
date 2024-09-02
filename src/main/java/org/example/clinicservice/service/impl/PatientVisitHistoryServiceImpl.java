package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.visitHistoryExceptions.VisitHistoryNotFoundException;
import org.example.clinicservice.repository.PatientRepository;
import org.example.clinicservice.repository.PatientVisitHistoryRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.PatientVisitHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientVisitHistoryServiceImpl implements PatientVisitHistoryService {

    private final PatientVisitHistoryRepository patientVisitHistoryRepository;
    private final PatientRepository patientRepository;
    private final SpecialistRepository specialistRepository;

    @Override
    public PatientVisitHistory getVisitHistoryById(UUID visitId) {
        return patientVisitHistoryRepository.findById(visitId)
                .orElseThrow(() -> new VisitHistoryNotFoundException(ErrorMessage.PATIENT_VISIT_HISTORY_NOT_FOUND));
    }

    @Override
    public Map<Optional<Patient>, List<PatientVisitHistory>> getVisitHistoryByPatient(UUID patientId) {
        List<PatientVisitHistory> visitHistory = patientVisitHistoryRepository.findByPatientId(patientId);

        Optional<Patient> patient = patientRepository.findById(patientId);

        return Collections.singletonMap(patient, visitHistory);
    }

    @Override
    public Map<Optional<Specialist>, List<PatientVisitHistory>> getVisitHistoryBySpecialist(UUID specialistId) {
        Optional<Specialist> specialist = specialistRepository.findById(specialistId);

        List<PatientVisitHistory> visitHistory = patientVisitHistoryRepository.findBySpecialistId(specialistId);

        return Collections.singletonMap(specialist, visitHistory);
    }

    @Override
    public Map<Patient, List<PatientVisitHistory>> getVisitHistoryByVisitDateTime(LocalDateTime visitDateTime) {
        List<PatientVisitHistory> visitHistory = patientVisitHistoryRepository.findByVisitDateTime(visitDateTime);

        if (visitHistory.isEmpty()) {
            throw new VisitHistoryNotFoundException(ErrorMessage.VISIT_HISTORY_NOT_FOUND_FOR_DATETIME);
        }

        return visitHistory.stream()
                .collect(Collectors.groupingBy(PatientVisitHistory::getPatient));
    }

    @Override
    public void saveVisitHistory(PatientVisitHistory visitHistory) {
        patientVisitHistoryRepository.save(visitHistory);
    }

    @Override
    public void deleteVisitHistory(UUID visitId) {
        getVisitHistoryById(visitId);
        patientVisitHistoryRepository.deleteById(visitId);
    }
}

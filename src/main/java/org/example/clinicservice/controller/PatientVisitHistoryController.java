package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.dto.PatientVisitHistoryDTO;
import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.mapper.PatientMapper;
import org.example.clinicservice.mapper.PatientVisitHistoryMapper;
import org.example.clinicservice.mapper.SpecialistMapper;
import org.example.clinicservice.service.interfeces.PatientVisitHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visit-history")
@RequiredArgsConstructor
public class PatientVisitHistoryController {

    private final PatientVisitHistoryService patientVisitHistoryService;
    private final PatientVisitHistoryMapper patientVisitHistoryMapper;
    private final SpecialistMapper specialistMapper;
    private final PatientMapper patientMapper;

    @GetMapping("/{visitId}")
    @ResponseStatus(HttpStatus.OK)
    public PatientVisitHistoryDTO getVisitHistoryById(@PathVariable UUID visitId) {
        PatientVisitHistory visitHistory = patientVisitHistoryService.getVisitHistoryById(visitId);
        return patientVisitHistoryMapper.toDto(visitHistory);
    }

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<PatientDTO, List<PatientVisitHistoryDTO>> getVisitHistoryByPatient(@PathVariable UUID patientId) {
        Map<Patient, List<PatientVisitHistory>> visitHistoryMap = patientVisitHistoryService.getVisitHistoryByPatient(patientId);
        return visitHistoryMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> patientMapper.toDto(entry.getKey()),
                        entry -> entry.getValue().stream().map(patientVisitHistoryMapper::toDto).collect(Collectors.toList())
                ));
    }

    @GetMapping("/specialist/{specialistId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<SpecialistDTO, List<PatientVisitHistoryDTO>> getVisitHistoryBySpecialist(@PathVariable UUID specialistId) {
        Map<Specialist, List<PatientVisitHistory>> visitHistoryMap = patientVisitHistoryService.getVisitHistoryBySpecialist(specialistId);
        return visitHistoryMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> specialistMapper.toDto(entry.getKey()),
                        entry -> entry.getValue().stream().map(patientVisitHistoryMapper::toDto).collect(Collectors.toList())
                ));
    }

    @GetMapping("/date-time")
    @ResponseStatus(HttpStatus.OK)
    public Map<PatientDTO, List<PatientVisitHistoryDTO>> getVisitHistoryByVisitDateTime(@RequestParam LocalDateTime visitDateTime) {
        Map<Patient, List<PatientVisitHistory>> visitHistoryMap = patientVisitHistoryService.getVisitHistoryByVisitDateTime(visitDateTime);
        return visitHistoryMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> patientMapper.toDto(entry.getKey()),
                        entry -> entry.getValue().stream().map(patientVisitHistoryMapper::toDto).collect(Collectors.toList())
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVisitHistory(@RequestBody PatientVisitHistoryDTO visitHistoryDTO) {
        PatientVisitHistory visitHistory = patientVisitHistoryMapper.toEntity(visitHistoryDTO);
        patientVisitHistoryService.saveVisitHistory(visitHistory);
    }

    @DeleteMapping("/{visitId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVisitHistory(@PathVariable UUID visitId) {
        patientVisitHistoryService.deleteVisitHistory(visitId);
    }
}


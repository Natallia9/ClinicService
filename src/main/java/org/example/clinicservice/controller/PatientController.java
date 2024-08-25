package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.mapper.PatientMapper;
import org.example.clinicservice.mapper.SpecialistMapper;
import org.example.clinicservice.service.interfeces.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final SpecialistMapper specialistMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return patients.stream()
                .map(patientMapper::toDto)
                .toList();
    }

    @GetMapping("/phone/{phoneNumber}")
    @ResponseStatus(HttpStatus.OK)
    public PatientDTO findByPhoneNumber(@PathVariable String phoneNumber) {
        Patient patient = patientService.findByPhoneNumber(phoneNumber);
        return patientMapper.toDto(patient);
    }

    @GetMapping("/{patientId}/specialists")
    @ResponseStatus(HttpStatus.OK)
    public Map<PatientDTO, List<SpecialistDTO>> findBySpecialists(@PathVariable UUID patientId) {
        Map<Patient, List<Specialist>> patientSpecialists = patientService.findBySpecialists(patientId);
        return patientSpecialists.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> patientMapper.toDto(entry.getKey()),
                        entry -> entry.getValue().stream()
                                .map(specialistMapper::toDto)
                                .collect(Collectors.toList())
                ));
    }
}



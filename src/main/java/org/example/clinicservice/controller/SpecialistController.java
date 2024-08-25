package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.enums.Department;
import org.example.clinicservice.mapper.PatientMapper;
import org.example.clinicservice.mapper.SpecialistMapper;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/specialists")
@RequiredArgsConstructor
public class SpecialistController {

    private final SpecialistService specialistService;
    private final SpecialistMapper specialistMapper;
    private final PatientMapper patientMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistDTO> getAllSpecialists() {
        List<Specialist> specialists = specialistService.getAllSpecialists();
        return specialists.stream()
                .map(specialistMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/specialization")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistDTO> findSpecialistsByAreaOfSpecialization(@RequestParam String areaOfSpecialization) {
        List<Specialist> specialists = specialistService.findSpecialistsByAreaOfSpecialization(areaOfSpecialization);
        return specialists.stream()
                .map(specialistMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/availability")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistDTO> findSpecialistsByAvailability(@RequestParam boolean availability) {
        List<Specialist> specialists = specialistService.findSpecialistsByAvailability(availability);
        return specialists.stream()
                .map(specialistMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/department")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialistDTO> findSpecialistsByDepartment(@RequestParam Department department) {
        List<Specialist> specialists = specialistService.findSpecialistsByDepartment(department);
        return specialists.stream()
                .map(specialistMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/patient/{specialistId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<SpecialistDTO, List<PatientDTO>> findSpecialistsByPatient(@PathVariable UUID specialistId) {
        Map<Specialist, List<Patient>> specialistsByPatient = specialistService.findSpecialistsByPatient(specialistId);
        return specialistsByPatient.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> specialistMapper.toDto(entry.getKey()),
                        entry -> entry.getValue().stream()
                                .map(patientMapper::toDto)
                                .collect(Collectors.toList())
                ));
    }
}


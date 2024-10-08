package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.MedicalRecordDTO;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.mapper.MedicalRecordMapper;
import org.example.clinicservice.service.interfeces.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordMapper medicalRecordMapper;

    @GetMapping("/{recordId}")
    @ResponseStatus(HttpStatus.OK)
    public MedicalRecordDTO getMedicalRecordById(@PathVariable UUID recordId) {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(recordId);
        return medicalRecordMapper.toDto(medicalRecord);
    }

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicalRecordDTO> getMedicalRecordsByPatientId(@PathVariable UUID patientId) {
        List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByPatientId(patientId);
        return medicalRecords.stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecordDTO saveMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordMapper.toEntity(medicalRecordDTO);
        medicalRecordService.saveMedicalRecord(medicalRecord);
        return medicalRecordMapper.toDto(medicalRecord);
    }

    @DeleteMapping("/{recordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicalRecord(@PathVariable UUID recordId) {
        medicalRecordService.deleteMedicalRecord(recordId);
    }
}


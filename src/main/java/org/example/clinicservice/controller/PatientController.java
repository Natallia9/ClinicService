package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.PatientAfterCreationDTO;
import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.mapper.PatientMapper;
import org.example.clinicservice.service.interfeces.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @GetMapping("/get/{id}")
    public Patient getPatientById(@PathVariable("id") UUID id){
        return patientService.getPatientById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientAfterCreationDTO patientDTO) {
        // Маппинг DTO в сущность Patient
        Patient patient = patientMapper.toEntity(patientDTO);

        // Сохранение пациента
        Patient savedPatient = patientService.createPatient(patient);

        // Маппинг сущности Patient обратно в DTO
        PatientDTO responseDTO = patientMapper.toDTO(savedPatient);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePatient(@PathVariable("id") UUID id) {
        patientService.deletePatient(id);
    }
}

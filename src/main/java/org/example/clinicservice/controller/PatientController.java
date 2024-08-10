package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {



//    @GetMapping("/get/{id}")
//    public Patient getPatientById(@PathVariable("id") UUID id){
//        Patient patient = patientService.
//        return patient;
//    }
//    @PostMapping("/add")
//    public Patient addNewPatient(@RequestBody Patient patient) {
//        Patient addPatient = patientService.savePatient(patient);
//        return addPatient;
    }


//
//    @PostMapping("/create")
//    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientAfterCreationDTO patientDTO) {
//        // Маппинг DTO в сущность Patient
//        Patient patient = patientMapper.toEntity(patientDTO);
//
//        // Сохранение пациента
//        Patient savedPatient = patientService.createPatient(patient);
//
//        // Маппинг сущности Patient обратно в DTO
//        PatientDTO responseDTO = patientMapper.toDTO(savedPatient);
//
//        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
//    }
//    @DeleteMapping("/delete/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deletePatient(@PathVariable("id") UUID id) {
//        patientService.deletePatient(id);
//    }
}

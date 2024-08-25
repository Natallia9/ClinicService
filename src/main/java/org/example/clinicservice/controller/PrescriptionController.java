package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.PrescriptionDTO;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.mapper.PrescriptionMapper;
import org.example.clinicservice.service.interfeces.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final PrescriptionMapper prescriptionMapper;

    @GetMapping("/{prescriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public PrescriptionDTO getPrescriptionById(@PathVariable UUID prescriptionId) {
        Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
        return prescriptionMapper.toDto(prescription);
    }

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PrescriptionDTO> getPrescriptionsByPatientId(@PathVariable UUID patientId) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatientId(patientId);
        return prescriptions.stream()
                .map(prescriptionMapper::toDto)
                .toList();
    }

    @GetMapping("/doctor/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PrescriptionDTO> getPrescriptionsByDoctorId(@PathVariable UUID doctorId) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByDoctorId(doctorId);
        return prescriptions.stream()
                .map(prescriptionMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        Prescription prescription = prescriptionMapper.toEntity(prescriptionDTO);
        prescriptionService.savePrescription(prescription);
    }

    @DeleteMapping("/{prescriptionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrescription(@PathVariable UUID prescriptionId) {
        prescriptionService.deletePrescription(prescriptionId);
    }
}



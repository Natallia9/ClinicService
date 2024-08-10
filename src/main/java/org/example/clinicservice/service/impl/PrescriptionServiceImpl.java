package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.repository.PrescriptionRepository;
import org.example.clinicservice.service.interfeces.PrescriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription getPrescriptionById(UUID prescriptionId) {

        return prescriptionRepository.findById(prescriptionId);
    }

    @Override
    public List<Prescription> getPrescriptionsByPatientId(UUID patientId) {

        return prescriptionRepository.findByPatientId(patientId);
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctorId(UUID doctorId) {

        return prescriptionRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Prescription> searchPrescriptionsByMedicationName(String medicationName) {

        return prescriptionRepository.findByMedicationNameContaining(medicationName);
    }

    @Override
    public List<Prescription> getPrescriptionsByDate(LocalDateTime prescriptionDate) {

        return prescriptionRepository.findByPrescriptionDate(prescriptionDate);
    }

    @Override
    public List<Prescription> getPrescriptionsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return prescriptionRepository.findByPrescriptionDateBetween(startDate, endDate);
    }

    @Override
    public void savePrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(UUID prescriptionId) {
        prescriptionRepository.deleteById(prescriptionId);
    }
}

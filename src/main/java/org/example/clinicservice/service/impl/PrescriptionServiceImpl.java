package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.prescriptionExceptions.PrescriptionAlreadyExistsException;
import org.example.clinicservice.exceptions.prescriptionExceptions.PrescriptionNotFoundException;
import org.example.clinicservice.exceptions.prescriptionExceptions.PrescriptionsNotFoundException;
import org.example.clinicservice.repository.PrescriptionRepository;
import org.example.clinicservice.service.interfeces.PrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription getPrescriptionById(UUID prescriptionId) {

        try {
            Prescription prescription = prescriptionRepository.findByPrescriptionId(prescriptionId);
            if(prescription == null){
                throw new PrescriptionNotFoundException(ErrorMessage.PRESCRIPTION_NOT_FOUND);
            }
            return prescription;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving prescription with id " + prescriptionId, e);
        }

    }

    @Override
    public List<Prescription> getPrescriptionsByPatientId(UUID patientId) {

        try {
            List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
            if (prescriptions.isEmpty()) {
                throw new PrescriptionsNotFoundException(ErrorMessage.PRESCRIPTIONS_NOT_FOUND);
            }
            return prescriptions;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error accessing data while retrieving prescriptions for patient ID: " + patientId, e);
        }
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctorId(UUID doctorId) {

        try {
            List<Prescription> prescriptions = prescriptionRepository.findByDoctorId(doctorId);
            if (prescriptions.isEmpty()) {
                throw new PrescriptionsNotFoundException(ErrorMessage.PRESCRIPTIONS_NOT_FOUND);
            }
            return prescriptions;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error accessing data for doctor ID: " + doctorId, e);
        }
    }

    @Override
    public void savePrescription(Prescription prescription) {

        if (prescriptionRepository.existsById(prescription.getPrescriptionId())) {
            throw new PrescriptionAlreadyExistsException(ErrorMessage.PRESCRIPTION_ALREADY_EXISTS);
        }
        try {
            prescriptionRepository.save(prescription);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to save prescription due to invalid argument: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletePrescription(UUID prescriptionId) {

        if (!prescriptionRepository.existsById(prescriptionId)) {
            throw new PrescriptionNotFoundException(ErrorMessage.PRESCRIPTION_NOT_FOUND);
        }

        try {
            prescriptionRepository.deleteById(prescriptionId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete prescription with ID: " + prescriptionId, e);
        }
    }
}


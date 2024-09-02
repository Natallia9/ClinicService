package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.exceptions.ErrorMessage;
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
        return prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new PrescriptionNotFoundException(ErrorMessage.PRESCRIPTION_NOT_FOUND));
    }

    @Override
    public List<Prescription> getPrescriptionsByPatientId(UUID patientId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
        if (prescriptions.isEmpty()) {
            throw new PrescriptionsNotFoundException(ErrorMessage.PRESCRIPTIONS_NOT_FOUND);
        }
        return prescriptions;
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctorId(UUID doctorId) {
        List<Prescription> prescriptions = prescriptionRepository.findByDoctorId(doctorId);
        if (prescriptions.isEmpty()) {
            throw new PrescriptionsNotFoundException(ErrorMessage.PRESCRIPTIONS_NOT_FOUND);
        }
        return prescriptions;
    }

    @Override
    public void savePrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(UUID prescriptionId) {
        getPrescriptionById(prescriptionId);
        prescriptionRepository.deleteById(prescriptionId);
    }
}

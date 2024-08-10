package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Prescription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PrescriptionService {

    Prescription getPrescriptionById(UUID prescriptionId);
    List<Prescription> getPrescriptionsByPatientId(UUID patientId);
    List<Prescription> getPrescriptionsByDoctorId(UUID doctorId);
    List<Prescription> searchPrescriptionsByMedicationName(String medicationName);
    List<Prescription> getPrescriptionsByDate(LocalDateTime prescriptionDate);
    List<Prescription> getPrescriptionsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    void savePrescription(Prescription prescriptionDTO);
    void deletePrescription(UUID prescriptionId);
}

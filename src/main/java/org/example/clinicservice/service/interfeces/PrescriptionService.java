package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Prescription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PrescriptionService {

    Prescription getPrescriptionById(UUID prescriptionId);
    List<Prescription> getPrescriptionsByPatientId(UUID patientId);
    List<Prescription> getPrescriptionsByDoctorId(UUID doctorId);
    void savePrescription(Prescription prescriptionDTO);
    void deletePrescription(UUID prescriptionId);
}

package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.MedicalRecord;

import java.util.List;
import java.util.UUID;

public interface MedicalRecordService {

    MedicalRecord getMedicalRecordById(UUID recordId);
    List<MedicalRecord> getMedicalRecordsByPatientId(UUID patientId);
    List<MedicalRecord> getMedicalRecordsByDoctorId(UUID doctorId);
    List<MedicalRecord> searchMedicalRecordsByPrescription(String prescription);
    List<MedicalRecord> findByDiagnoseContaining(String diagnose);
    List<MedicalRecord> findByDoctorConclusionContaining(String doctorConclusion);
    void saveMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecord(UUID recordId);
}

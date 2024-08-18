package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.MedicalRecord;

import java.util.List;
import java.util.UUID;

public interface MedicalRecordService {

    MedicalRecord getMedicalRecordById(UUID recordId);
    List<MedicalRecord> getMedicalRecordsByPatientId(UUID patientId);
    void saveMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecord(UUID recordId);
}

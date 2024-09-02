package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordNotFoundException;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordsNotFoundException;
import org.example.clinicservice.repository.MedicalRecordRepository;
import org.example.clinicservice.service.interfeces.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord getMedicalRecordById(UUID recordId) {
        return medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new MedicalRecordNotFoundException(ErrorMessage.MEDICAL_RECORD_NOT_FOUND));
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatientId(UUID patientId) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatientId(patientId);
        if (medicalRecords.isEmpty()) {
            throw new MedicalRecordsNotFoundException(ErrorMessage.MEDICAL_RECORDS_NOT_FOUND);
        }
        return medicalRecords;
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public void deleteMedicalRecord(UUID recordId) {
        getMedicalRecordById(recordId);
        medicalRecordRepository.deleteById(recordId);
    }
}

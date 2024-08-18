package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordAlreadyExistsException;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordNotFoundException;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordSaveException;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordsNotFoundException;
import org.example.clinicservice.repository.MedicalRecordRepository;
import org.example.clinicservice.repository.SpecialistRepository;
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

        try {
            MedicalRecord medicalRecord = medicalRecordRepository.findByRecordId(recordId);
            if(recordId == null){
                throw new MedicalRecordNotFoundException(ErrorMessage.MEDICAL_RECORD_NOT_FOUND);
            }
            return medicalRecord;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving medical record with id " + recordId, e);
        }
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatientId(UUID patientId) {

        try {
            List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatientId(patientId);
            if (medicalRecords.isEmpty()) {
                throw new MedicalRecordsNotFoundException(ErrorMessage.MEDICAL_RECORDS_NOT_FOUND);
            }
            return medicalRecords;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve medical records for patient ID: " + patientId, e);
        }
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord) {

        if (medicalRecordRepository.existsById(medicalRecord.getRecordId())){
            throw new MedicalRecordAlreadyExistsException(ErrorMessage.MEDICAL_RECORD_ALREADY_EXISTS);
        }
        try {
            medicalRecordRepository.save(medicalRecord);
        } catch (Exception e) {
            throw new MedicalRecordSaveException("Failed to save medical record due to an unexpected error: " + e.getMessage());
        }
    }

    @Override
    public void deleteMedicalRecord(UUID recordId) {

        if (!medicalRecordRepository.existsById(recordId)) {
            throw new MedicalRecordNotFoundException(ErrorMessage.MEDICAL_RECORD_NOT_FOUND);
        }

        try {
            medicalRecordRepository.deleteById(recordId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete medical record with ID: " + recordId, e);
        }
    }
    }


package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.MedicalRecord;
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

        return medicalRecordRepository.findByRecordId(recordId);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatientId(UUID patientId) {

        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByDoctorId(UUID doctorId) {

        return medicalRecordRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<MedicalRecord> searchMedicalRecordsByPrescription(String prescription) {

        return medicalRecordRepository.findByPrescriptionContaining(prescription);
    }

    @Override
    public List<MedicalRecord> findByDiagnoseContaining(String diagnose) {
        return medicalRecordRepository.findByDiagnoseContaining(diagnose);
    }

    @Override
    public List<MedicalRecord> findByDoctorConclusionContaining(String doctorConclusion) {
        return medicalRecordRepository.findByDoctorConclusionContaining(doctorConclusion);
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public void deleteMedicalRecord(UUID recordId) {
        medicalRecordRepository.deleteById(recordId);
    }
}

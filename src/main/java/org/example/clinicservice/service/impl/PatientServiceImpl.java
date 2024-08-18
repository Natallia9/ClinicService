package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.patientExceptions.PatientNotFoundException;
import org.example.clinicservice.exceptions.patientExceptions.PatientRetrievalException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecialistsNotFoundException;
import org.example.clinicservice.repository.PatientRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.PatientService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final SpecialistRepository specialistRepository;

    @Override
    public List<Patient> getAllPatients() {

        try {
            List<Patient> patients = patientRepository.findAll();
            if (patients.isEmpty()) {
                throw new PatientRetrievalException(ErrorMessage.NO_PATIENTS_FOUND);
            }
            return patients;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to access data: " + e.getMessage(), e);
        }
    }

    @Override
    public Patient findByPhoneNumber(String phoneNumber) {

        try {
            Patient patient = patientRepository.findByPhoneNumber(phoneNumber);
            if(patient == null){
                throw new PatientNotFoundException(ErrorMessage.PATIENT_NOT_FOUND);
            }
            return patient;
        } catch (Exception e) {
            throw new RuntimeException("Patient with phone number " + phoneNumber + " not found");
        }
    }

    @Override
    public Map<Patient, List<Specialist>> findBySpecialists(UUID patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(ErrorMessage.PATIENT_NOT_FOUND));

        List<Specialist> specialists = specialistRepository.findByPatientId(patientId);

        if (specialists.isEmpty()) {
            throw new SpecialistsNotFoundException(ErrorMessage.SPECIALISTS_NOT_FOUND);
        }

        return Collections.singletonMap(patient, specialists);
    }

}

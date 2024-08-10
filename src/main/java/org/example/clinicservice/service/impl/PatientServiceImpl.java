package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.repository.PatientRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {

        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsByDoctorId(UUID doctorId) {
        return patientRepository.findByDoctorId(doctorId);
    }


    @Override
    public Patient findByPhoneNumber(String phoneNumber) {

        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Patient> findByFirstNameAndLastName(String firstName, String lastName) {

        return patientRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Patient> findByMedicalRecordsIsNotEmpty() {

        return patientRepository.findByMedicalRecordsIsNotEmpty();
    }

    @Override
    public List<Patient> findBySpecialists(Specialist specialist) {

        return patientRepository.findBySpecialists(specialist);
    }

}

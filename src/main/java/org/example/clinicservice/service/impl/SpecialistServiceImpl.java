package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.enums.Department;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.patientExceptions.PatientsNotFoundException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecialistNotFoundException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecialistsNotFoundException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecializationNotFoundException;
import org.example.clinicservice.repository.PatientRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;
    private final PatientRepository patientRepository;

    @Override
    public List<Specialist> getAllSpecialists() {
       return specialistRepository.findAll();

    }

    @Override
    public List<Specialist> findSpecialistsByAreaOfSpecialization(String areaOfSpecialization) {
        List<Specialist> specialists = specialistRepository.findByAreaOfSpecialization(areaOfSpecialization);
        if (specialists.isEmpty()) {
            throw new SpecializationNotFoundException(ErrorMessage.SPECIALISTS_WITH_PROFILE_NOT_FOUND);
        }
        return specialists;
    }

    @Override
    public List<Specialist> findSpecialistsByAvailability(boolean availability) {
        List<Specialist> specialists = specialistRepository.findByAvailability(availability);
        if (specialists.isEmpty()) {
            throw new SpecialistsNotFoundException("No specialists found with the specified availability");
        }
        return specialists;
    }

    @Override
    public List<Specialist> findSpecialistsByDepartment(Department department) {

        List<Specialist> specialists = specialistRepository.findByDepartment(department);
        if (specialists.isEmpty()) {
            throw new SpecialistsNotFoundException("There are no specialists in the department");
        }
        return specialists;
    }

    @Override
    public Map<Specialist, List<Patient>> findSpecialistsByPatient(UUID specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new SpecialistNotFoundException(ErrorMessage.SPECIALIST_NOT_FOUND));

        List<Patient> patients = patientRepository.findByDoctorId(specialistId);
        if (patients.isEmpty()) {
            throw new PatientsNotFoundException(ErrorMessage.PATIENTS_NOT_FOUND);
        }

        return Collections.singletonMap(specialist, patients);
    }
}

//        List<Specialist> specialists = specialistRepository.findByPatientPhoneNumberOrPatientEmail(
//                patient.getPhoneNumber(),
//                patient.getEmail()
//        );
//
//        if (specialists.isEmpty()) {
//            throw new InvalidPatientException(ErrorMessage.INVALID_PATIENT);
//        }
//
//        try {
//            return specialists;
//        } catch (Exception e) {
//            throw new RuntimeException("No specialists have been found" + e.getMessage(), e);
//        }

//        Patient foundPatientByPhoneNumber = patientRepository.findByPhoneNumber(patient.getPhoneNumber());
//        Patient foundPatientByEmail = patientRepository.findByEmail(patient.getEmail());
//
//        if (foundPatientByPhoneNumber == null && foundPatientByEmail == null) {
//            throw new InvalidPatientException(ErrorMessage.INVALID_PATIENT);
//        }
//
//        Patient foundPatient = foundPatientByPhoneNumber != null ? foundPatientByPhoneNumber : foundPatientByEmail;
//
//        List<Specialist> specialists = specialistRepository.findByPatients(foundPatient);
//
//        if (specialists.isEmpty()) {
//            throw new InvalidPatientException(ErrorMessage.INVALID_PATIENT);
//        }
//
//        return specialists;



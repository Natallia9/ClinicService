package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.enums.Department;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.patientExeptions.InvalidPatientException;
import org.example.clinicservice.exceptions.specialistExeptions.DepartmentNotFoundException;
import org.example.clinicservice.exceptions.specialistExeptions.SpecialistsNotFoundException;
import org.example.clinicservice.exceptions.specialistExeptions.SpecializationNotFoundException;
import org.example.clinicservice.repository.PatientRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;
    private final PatientRepository patientRepository;

    @Override
    public List<Specialist> getAllSpecialists() {
        try {
            List<Specialist> specialists = specialistRepository.findAll();
            if (specialists.isEmpty()) {
                throw new SpecialistsNotFoundException("No specialists found");
            }
            return specialists;
        } catch (IllegalStateException e) {
            throw new RuntimeException("An unexpected condition has occurred", e);
        }
    }

    @Override
    public List<Specialist> findSpecialistsByAreaOfSpecialization(String areaOfSpecialization) {
        try {
            List<Specialist> specialists = specialistRepository.findByAreaOfSpecialization(areaOfSpecialization);
            if (specialists.isEmpty()) {
                throw new SpecializationNotFoundException(ErrorMessage.SPECIALISTS_WITH_PROFILE_NOT_FOUND);
            }
            return specialists;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error with the conclusion of specialists in this specialization: " + e);
        }
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
        if (department == null) {
            throw new DepartmentNotFoundException(ErrorMessage.DEPARTMENT_NOT_EXIST);
        }
        try {
            List<Specialist> specialists = specialistRepository.findByDepartment(department);
            if (specialists.isEmpty()) {
                throw new SpecialistsNotFoundException("There are no specialists in the department");
            }
            return specialists;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("The problem with finding specialists " + e);
        }
    }

    @Override
    public List<Specialist> findSpecialistsByPatient(Patient patient) {

        List<Specialist> specialists = specialistRepository.findByPatientPhoneNumberOrPatientEmail(
                patient.getPhoneNumber(),
                patient.getEmail()
        );

        if (specialists.isEmpty()) {
            throw new InvalidPatientException(ErrorMessage.INVALID_PATIENT);
        }

        return specialists;

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
    }
}

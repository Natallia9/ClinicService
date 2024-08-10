package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.enums.Department;
import org.example.clinicservice.exceptions.ExceptionsService;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;

    @Override
    public List<Specialist> getAllSpecialists() {
        List<Specialist> specialists = specialistRepository.findAll();
        if (specialists.isEmpty()) {
            throw new ExceptionsService.SpecialistNotFoundException("No specialists found.");
        }
        return specialists;
    }

    @Override
    public List<Specialist> findSpecialistsByAreaOfSpecialization(String areaOfSpecialization) {
        return specialistRepository.findByAreaOfSpecialization(areaOfSpecialization);
    }

    @Override
    public List<Specialist> findSpecialistsByContactInformation(String contactInformation) {
        return specialistRepository.findByContactInformation(contactInformation);
    }

    @Override
    public List<Specialist> findSpecialistsByAvailability(boolean availability) {
        return specialistRepository.findByAvailability(availability);
    }

    @Override
    public List<Specialist> findSpecialistsByDepartment(Department department) {
        return specialistRepository.findByDepartment(department);
    }

    @Override
    public List<Specialist> findSpecialistsByPatient(Patient patient) {
        return specialistRepository.findByPatients(patient);
    }
}


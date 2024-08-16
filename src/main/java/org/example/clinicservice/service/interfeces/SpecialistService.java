package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.enums.Department;

import java.util.List;
import java.util.UUID;

public interface SpecialistService {

    List<Specialist> getAllSpecialists();
    List<Specialist> findSpecialistsByAreaOfSpecialization(String areaOfSpecialization);
    List<Specialist> findSpecialistsByAvailability(boolean availability);
    List<Specialist> findSpecialistsByDepartment(Department department);
    List<Specialist> findSpecialistsByPatient(Patient patient);

}

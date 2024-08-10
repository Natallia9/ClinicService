package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, UUID> {

    List<Specialist> findByAreaOfSpecialization(String areaOfSpecialization);
    List<Specialist> findByContactInformation(String contactInformation);
    List<Specialist> findByAvailability(boolean availability);
    List<Specialist> findByDepartment(Department department);
    List<Specialist> findByPatients(Patient patient);

}

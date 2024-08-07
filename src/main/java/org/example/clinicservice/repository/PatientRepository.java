package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Patient getPatientById(UUID id);
    Patient save(Patient patient);
    void deleteById(UUID id);
}

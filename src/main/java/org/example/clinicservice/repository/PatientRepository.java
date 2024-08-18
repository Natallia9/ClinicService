package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    List<Patient> findByDoctorId(UUID doctorId);
    Patient findByPhoneNumber(String phoneNumber);
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
    List<Patient> findByMedicalRecordsIsNotEmpty();
    List<Patient> findBySpecialists(Specialist specialist);
}

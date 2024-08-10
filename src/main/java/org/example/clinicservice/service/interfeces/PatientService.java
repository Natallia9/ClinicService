package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {

    List<Patient> getAllPatients();
    List<Patient> getPatientsByDoctorId(UUID doctorId);
    Patient findByPhoneNumber(String phoneNumber);
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
    List<Patient> findByMedicalRecordsIsNotEmpty();
    List<Patient> findBySpecialists(Specialist specialist);

}

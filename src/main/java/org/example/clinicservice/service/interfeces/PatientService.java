package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {

    List<Patient> getAllPatients();
    Patient findByPhoneNumber(String phoneNumber);
    Map<Patient, List<Specialist>> findBySpecialists(UUID patientId);

}

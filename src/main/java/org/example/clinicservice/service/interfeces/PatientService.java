package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.dto.PatientAfterCreationDTO;
import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.entity.Patient;
import java.util.UUID;

public interface PatientService {
    Patient getPatientById(UUID id);
    PatientDTO createPatient(PatientAfterCreationDTO patientDTO);
    void deletePatient(UUID id);
}


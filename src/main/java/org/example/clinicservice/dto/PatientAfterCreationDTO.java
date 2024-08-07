package org.example.clinicservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PatientAfterCreationDTO {
    private String patientId;
    private String PatientStatus = "PATIENT CREATED";

}

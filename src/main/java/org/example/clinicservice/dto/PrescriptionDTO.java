package org.example.clinicservice.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PrescriptionDTO {

    private String medicationName;
    private String dosage;
    private String instructions;
    private UUID doctorId;
    private UUID patientId;
}

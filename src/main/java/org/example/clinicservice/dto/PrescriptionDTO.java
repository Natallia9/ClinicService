package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
@Schema(description = "DTO for representing a prescription, including medication details, dosage, instructions, and related identifiers.")
public class PrescriptionDTO {

    private String medicationName;
    private String dosage;
    private String instructions;
    private UUID doctorId;
    private UUID patientId;
}

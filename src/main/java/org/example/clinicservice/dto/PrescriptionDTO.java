package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;

import java.util.UUID;

@Data
@Schema(description = "DTO for representing a prescription, including medication details, dosage, instructions, and related identifiers.")
public class PrescriptionDTO {

    @NotNull(message = "This field cannot be empty")
    private UUID prescriptionId;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 100, message = "Medication name cannot exceed 100 characters")
    private String medicationName;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "Dosage cannot exceed 50 characters")
    private String dosage;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 255, message = "Instructions cannot exceed 255 characters")
    private String instructions;

    @NotNull(message = "This field cannot be empty")
    private Specialist doctor;

    @NotNull(message = "This field cannot be empty")
    private Patient patient;
}

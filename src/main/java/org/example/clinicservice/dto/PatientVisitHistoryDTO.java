package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.clinicservice.entity.MedicalRecord;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Schema(description = "DTO for patient visit history.")
public class PatientVisitHistoryDTO {

    @NotNull(message = "This field cannot be empty")
    private UUID visitId;

    @NotNull(message = "This field cannot be empty")
    private UUID patientId;

    @NotNull(message = "This field cannot be empty")
    private UUID specialistId;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String visitType;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String patientCondition;

    @NotNull(message = "This field cannot be empty")
    @Future(message = "Select time and date of visit")
    private LocalDateTime visitDateTime;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String purpose;

    @NotNull(message = "This field cannot be empty")
    private UUID medicalRecordId;
}

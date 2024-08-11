package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Schema(description = "DTO for patient visit history.")
public class PatientVisitHistoryDTO {

    private UUID visitId;
    private UUID patientId;
    private UUID specialistId;
    private String visitType;
    private String patientCondition;
    private LocalDateTime visitDateTime;
    private String purpose;
    private UUID medicalRecordId;
}

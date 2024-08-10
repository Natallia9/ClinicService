package org.example.clinicservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
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

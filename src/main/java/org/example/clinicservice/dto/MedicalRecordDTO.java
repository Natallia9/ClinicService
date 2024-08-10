package org.example.clinicservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class MedicalRecordDTO {

    private UUID recordId;
    private UUID patientId;
    private UUID doctorId;
    private String diagnose;
    private String doctorConclusion;
    private String prescription;
    private List<String> medicalProcedures;
    private List<LabReportDTO> labReports;
}

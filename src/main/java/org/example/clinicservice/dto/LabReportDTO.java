package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.clinicservice.entity.MedicalRecord;

import java.time.LocalDateTime;
@Data
@Schema(description = "DTO for a laboratory report, including report details and associated medical record.")
public class LabReportDTO {

    private String reportName;
    private String reportContent;
    private LocalDateTime reportDate;
    private String results;
    private MedicalRecord medicalRecord;
}

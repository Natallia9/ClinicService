package org.example.clinicservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class LabReportDTO {

    private UUID reportId;
    private String reportName;
    private String reportContent;
    private LocalDateTime reportDate;
    private String results;
}

package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.clinicservice.entity.MedicalRecord;

import java.time.LocalDateTime;
@Data
@Schema(description = "DTO for a laboratory report, including report details and associated medical record.")
public class LabReportDTO {

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String reportName;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 100, message = "The length of characters in the field must not exceed 100")
    private String reportContent;

    @NotNull(message = "This field cannot be empty")
    @PastOrPresent(message = "Date of issue of laboratory report")
    private LocalDateTime reportDate;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 150, message = "The length of characters in the field must not exceed 150")
    private String results;

    @NotNull(message = "This field cannot be empty")
    @Valid
    private MedicalRecord medicalRecord;
}

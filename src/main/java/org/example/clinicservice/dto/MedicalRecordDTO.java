package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.entity.Specialist;

import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "DTO for representing a medical record, including patient details, doctor information, diagnosis, conclusions, prescription, medical procedures, and lab reports.")
public class MedicalRecordDTO {

    @NotNull(message = "This field cannot be empty")
    private UUID recordId;

    @NotNull(message = "This field cannot be empty")
    private UUID patientId;

    @NotNull(message = "This field cannot be empty")
    private UUID doctorId;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String diagnose;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String doctorConclusion;

    @NotNull(message = "This field cannot be empty")
    private UUID prescriptionId;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private List<String> medicalProcedure;

    @NotNull(message = "This field cannot be empty")
    private List<UUID> labReportIds;
}

package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "DTO for representing a medical record, including patient details, doctor information, diagnosis, conclusions, prescription, medical procedures, and lab reports.")
public class MedicalRecordDTO {

    private UUID recordId;

    @NotNull(message = "Поле patientId не может быть пустым")
    private UUID patientId;

    @NotNull(message = "Поле doctorId не может быть пустым")
    private UUID doctorId;

    @NotEmpty(message = "Поле diagnose не может быть пустым")
    private String diagnose;

    @NotEmpty(message = "Поле doctorConclusion не может быть пустым")
    private String doctorConclusion;

    private String prescription;

    @NotEmpty(message = "Поле medicalProcedure не может быть пустым")
    private List<String> medicalProcedure;

    @NotEmpty(message = "Поле labReportIds не может быть пустым")
    private List<UUID> labReportIds;

//    @NotNull(message = "This field cannot be empty")
//    private UUID patientId;
//
//    @NotNull(message = "This field cannot be empty")
//    private UUID doctorId;
//
//    @NotEmpty(message = "This field cannot be empty")
//    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
//    private String diagnose;
//
//    @NotEmpty(message = "This field cannot be empty")
//    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
//    private String doctorConclusion;
//
//    @NotNull(message = "This field cannot be empty")
//    private UUID prescriptionId;
//
//    @NotEmpty(message = "This field cannot be empty")
//    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
//    private List<String> medicalProcedure;
//
//    @NotNull(message = "This field cannot be empty")
//    private List<UUID> labReportIds;
}

package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;

import java.util.List;
@Data
@Schema(description = "DTO for representing a medical record, including patient details, doctor information, diagnosis, conclusions, prescription, medical procedures, and lab reports.")
public class MedicalRecordDTO {

    private Patient patient;
    private Specialist doctor;
    private String diagnose;
    private String doctorConclusion;
    private String prescription;
    private List<String> medicalProcedure;
    private List<LabReport> labReports;
}

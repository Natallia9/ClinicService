package org.example.clinicservice;

import org.example.clinicservice.entity.enums.Diagnoses;

import java.util.List;
import java.util.UUID;

public class MedicalRecord {
    private UUID recordId;
    private Patient patient;
    private Specialist doctor;
    private Diagnoses diagnoses;
    private String prescription;
    private List<String> labReports;

}

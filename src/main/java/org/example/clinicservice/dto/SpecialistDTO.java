package org.example.clinicservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SpecialistDTO {
    private UUID specialistId;
    private String areaOfSpecialization;
    private String experience;
    private String contactInformation;
    private boolean	availability;
    private String languagesSpoken;
}

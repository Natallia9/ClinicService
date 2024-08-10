package org.example.clinicservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SpecialistDTO {

    private String lastName;
    private String firstName;
    private UUID specialistId;
    private String experience;
    private boolean	availability;
    private String languagesSpoken;
}

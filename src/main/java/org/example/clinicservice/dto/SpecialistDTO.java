package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "DTO for a specialist with personal details, experience, availability, and spoken languages.")
public class SpecialistDTO {

    private String lastName;
    private String firstName;
    private UUID specialistId;
    private String experience;
    private boolean	availability;
    private String languagesSpoken;
}

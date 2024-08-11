package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "DTO for patient details.")
public class PatientDTO {

    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String age;
    private String sex;
    private String address;
    private String emerg;
}

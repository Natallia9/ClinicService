package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "DTO for patient details")
public class PatientDTO {


    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+\\d{11,15}", message = "Phone number is invalid")
    private String phoneNumber;

    @NotEmpty(message = "Age cannot be empty")
    private String age;

    @NotEmpty(message = "Sex cannot be empty")
    private String sex;

    @NotEmpty(message = "Address cannot be empty")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @NotEmpty(message = "Emergency contact cannot be empty")
    @Pattern(regexp = "\\+\\d{11,15}", message = "Emergency contact is invalid")
    private String emergencyContact;
}

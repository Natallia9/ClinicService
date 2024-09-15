package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "DTO for patient details")
public class PatientDTO {


    @NotEmpty(message = "This field cannot be empty")
    @Pattern(regexp = "\\+\\d{11,15}", message = "Phone number is invalid")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String phoneNumber;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 1, max = 3, message = "The length of characters in the field must not exceed 3")
    private String age;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 1, max = 10, message = "The length of characters in the field must not exceed 10")
    private String sex;

    @NotEmpty(message = "This field cannot be empty")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @NotEmpty(message = "This field cannot be empty")
    @Pattern(regexp = "\\+\\d{11,15}", message = "Emergency contact is invalid")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String emergencyContact;
}

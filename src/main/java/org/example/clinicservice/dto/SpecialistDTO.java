package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.clinicservice.entity.Appointment;
import org.example.clinicservice.entity.enums.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "DTO for a specialist with personal details, experience, availability, and spoken languages.")
public class SpecialistDTO {

    @NotNull(message = "This field cannot be empty")
    @Valid
    private UserDTO user;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String areaOfSpecialization;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String experience;

    @NotEmpty(message = "Contact information cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    @Pattern(regexp = "^\\+\\d{11,15}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Emergency contact is invalid")
    private String contactInformation;

    private boolean availability;

    @NotEmpty(message = "Contact information cannot be empty")
    @Size(min = 1, max = 50, message = "The length of characters in the field must not exceed 50")
    private String languagesSpoken;

    @NotNull(message = "This field cannot be empty")
    @Valid
    private Department department;

    @NotNull(message = "This field cannot be empty")
    @Valid
    private List<Appointment> appointments = new ArrayList<>();


}

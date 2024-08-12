package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Area of specialization cannot be empty")
    @Size(min = 1, max = 50, message = "Area of specialization cannot exceed 100 characters")
    private String areaOfSpecialization;

    @NotEmpty(message = "Experience cannot be empty")
    @Size(min = 1, max = 50, message = "Experience cannot exceed 50 characters")
    private String experience;

    @NotEmpty(message = "Contact information cannot be empty")
    @Size(min = 1, max = 50, message = "Contact information cannot exceed 255 characters")
    @Pattern(regexp = "^\\+\\d{11,15}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Emergency contact is invalid")
    private String contactInformation;

    private boolean availability;

    @NotEmpty(message = "Languages spoken cannot be empty")
    @Size(min = 1, max = 50, message = "Languages spoken cannot exceed 255 characters")
    private String languagesSpoken;

    private Department department;

    private List<Appointment> appointments = new ArrayList<>();

}

package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.entity.enums.UserType;

import java.util.Set;
import java.util.UUID;
@Data
@Schema(description = "DTO for transferring user information")
public class UserDTO {

    private UUID userId;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstName;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String userName;

    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @NotEmpty(message = "The field cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "This field cannot be empty")
    @Valid
    private UserType userType;

    @NotNull(message = "This field cannot be empty")
    @Valid
    private Set<Roles> roles;
}

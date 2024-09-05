package org.example.clinicservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Sign-up request")
public class SignUpRequest {

    @Schema(description = "Username", example = "Jon")
    @Size(min = 1, max = 50, message = "Username must contain between 1 and 50 characters")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "Email address", example = "jondoe@gmail.com")
    @Size(min = 1, max = 50, message = "Email address must contain between 1 and 50 characters")
    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Email address must be in the format user@example.com")
    private String email;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 6, max = 100, message = "Password length must be no more than 100 characters")
    private String password;
}


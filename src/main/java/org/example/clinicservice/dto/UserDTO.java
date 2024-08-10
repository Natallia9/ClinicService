package org.example.clinicservice.dto;

import lombok.Data;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.entity.enums.UserType;

import java.util.Set;
import java.util.UUID;
@Data
public class UserDTO {

    private UUID userId;
    private String lastName;
    private String firstName;
    private String email;
    private UserType userType;
    private Set<Roles> roles;
}

package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.UserType;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a user in the system.")
public class User {

    /**
     * Unique identifier of the user.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id")
    @Schema(description = "Unique identifier of the user")
    private UUID userId;

    /**
     * Last name of the user.
     */
    @Column(name = "last_name")
    @NotNull(message = "Last name must not be null")
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    /**
     * First name of the user.
     */
    @Column(name = "first_name")
    @NotNull(message = "First name must not be null")
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    /**
     * Username for login purposes.
     */
    @Column(name = "user_name")
    @NotNull(message = "User name must not be null")
    @Schema(description = "Username of the user", example = "johndoe")
    private String userName;

    /**
     * Password of the user (encrypted).
     */
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Schema(description = "Password of the user")
    private String password;

    /**
     * Email address of the user.
     */
    @Column(name = "email", unique = true, nullable = false)
    @Email
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    /**
     * Type of the user (e.g., Patient, Doctor, Administrator).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    @Schema(description = "Type of the user (e.g., PATIENT, DOCTOR, ADMINISTRATOR)")
    private UserType userType;

    /**
     * Roles associated with the user.
     */
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Schema(description = "Roles assigned to the user")
    private Set<Roles> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lastName, firstName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

}

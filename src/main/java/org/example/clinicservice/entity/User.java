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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a user in the system.")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id")
    @Schema(description = "Unique identifier of the user")
    private UUID userId;

    @Column(name = "last_name")
    @NotNull(message = "Last name must not be null")
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Column(name = "first_name")
    @NotNull(message = "First name must not be null")
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Column(name = "user_name")
    @NotNull(message = "User name must not be null")
    @Schema(description = "Username of the user", example = "johndoe")
    private String userName;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Schema(description = "Password of the user")
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", insertable = false, updatable = false)  // Поле дискриминатора
    @Schema(description = "Type of the user (e.g., PATIENT, DOCTOR, ADMINISTRATOR)")
    private UserType userType;

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
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
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

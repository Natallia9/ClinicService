package org.example.clinicservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "system_owner")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents the owner of the system.")
public class SystemOwner {

    /**
     * Unique identifier for the system owner.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "owner_id")
    @Schema(description = "Unique identifier of the system owner.")
    private UUID ownerId;

    /**
     * First name of the system owner.
     */
    @Column(name = "first_name")
    @Schema(description = "First name of the system owner.", example = "John")
    private String firstName;

    /**
     * Last name of the system owner.
     */
    @Column(name = "last_name")
    @Schema(description = "Last name of the system owner.", example = "Doe")
    private String lastName;

    /**
     * Phone number of the system owner.
     */
    @Column(name = "phone_number")
    @Schema(description = "Phone number of the system owner.", example = "+1234567890")
    private String phoneNumber;

    /**
     * Login username for the system owner.
     */
    @Column(name = "login")
    @Schema(description = "Login username of the system owner.", example = "adminuser")
    private String login;

    /**
     * Email address of the system owner.
     */
    @Column(name = "email")
    @Email
    @Schema(description = "Email address of the system owner.", example = "admin@example.com")
    private String email;

    /**
     * Password of the system owner.
     */
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Schema(description = "Password of the system owner.")
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemOwner admin = (SystemOwner) o;
        return Objects.equals(ownerId, admin.ownerId) && Objects.equals(firstName, admin.firstName) && Objects.equals(lastName, admin.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "SystemOwner{" +
                "ownerId=" + ownerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }
}

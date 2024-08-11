package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "last_name")
    @NotNull(message = "Last name must not be null")
    private String lastName;

    @Column(name = "first_name")
    @NotNull(message = "First name must not be null")
    private String firstName;

    @Column(name = "user_name")
    @NotNull(message = "User name must not be null")
    private String userName;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private char password;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
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

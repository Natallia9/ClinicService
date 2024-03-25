package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.UserType;

import javax.management.relation.Role;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor

public class User {

    @Id
    @Column(name = "id")
    private UUID userId;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @OneToOne
    @JoinColumn(name = "user_name")
    private String userName;

    private String password;

    @Column(name = "email")
    private String email;

    private UserType userType;

    private Set<Role> roles;

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

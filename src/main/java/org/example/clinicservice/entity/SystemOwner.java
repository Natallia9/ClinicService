package org.example.clinicservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "system_owner")
@Getter
@Setter
@NoArgsConstructor
public class SystemOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

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
}

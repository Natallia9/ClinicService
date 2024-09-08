package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.RoleName;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a role in the system.")
public class Roles {

    /**
     * Unique identifier of the role.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "role_id")
    @Schema(description = "Unique identifier of the role.")
    private UUID roleId;

    /**
     * Name of the role.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    @Schema(description = "Name of the role", example = "ADMIN")
    private RoleName roleName;

    /**
     * Authorities associated with the role.
     */
    @JsonManagedReference
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Schema(description = "Authorities associated with the role.")
    private Set<Authority> authorities = new HashSet<>();

    /**
     * Users that have the role.
     */
    @JsonManagedReference
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Schema(description = "Users that are assigned this role.")
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(roleId, roles.roleId) && Objects.equals(roleName, roles.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}

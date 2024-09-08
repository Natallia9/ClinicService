package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents the authority or permission granted to a role.")
public class Authority {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "authority_id")
    @Schema(description = "Unique identifier of the authority.", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID authorityId;

    @Column(name = "authority")
    @Schema(description = "The authority or permission name.", example = "READ_DOCUMENT")
    private String authority;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    @Schema(description = "Roles that are associated with this authority.")
    private Set<Roles> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(authorityId, authority1.authorityId) && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", authority='" + authority + '\'' +
                ", roles=" + roles +
                '}';
    }
}

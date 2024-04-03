package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "specialists_repository")
@Getter
@Setter
@NoArgsConstructor
public class SpecialistRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "repository_id")
    private UUID repSpecId;

    @ManyToMany(mappedBy = "specialists")
    private List<Specialist> specialists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialistRepository that = (SpecialistRepository) o;
        return Objects.equals(repSpecId, that.repSpecId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repSpecId);
    }
}

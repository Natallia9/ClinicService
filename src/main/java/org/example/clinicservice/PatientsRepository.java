package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "patients_repository")
@Getter
@Setter
@NoArgsConstructor
public class PatientsRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "repository_id")
    private UUID repositoryId;

    @ManyToMany(mappedBy = "patient_repository")
    private List<Patient> patients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientsRepository that = (PatientsRepository) o;
        return Objects.equals(repositoryId, that.repositoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repositoryId);
    }
}

package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.Diagnoses;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private UUID recordId;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Specialist doctor;

    @Enumerated(EnumType.STRING)
    private Diagnoses diagnoses;

    @Column(name = "prescription")
    private String prescription;

    @ElementCollection
    @CollectionTable(name = "lab_reports", joinColumns = @JoinColumn(name = "record_id"))
    @Column(name = "lab_report")
    private List<String> labReports;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord that = (MedicalRecord) o;
        return Objects.equals(recordId, that.recordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }
}

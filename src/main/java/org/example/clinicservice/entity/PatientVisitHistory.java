package org.example.clinicservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "patient_visits")
@Getter
@Setter
@NoArgsConstructor
public class PatientVisitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visit_id")
    private UUID visitId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @Column(name = "visit_type")
    private String visitType;

    @Column(name = "patient_condition")
    private String patientCondition;

    @Column(name = "visit_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime visitDateTime;

    @Column(name = "purpose")
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientVisitHistory that = (PatientVisitHistory) o;
        return Objects.equals(visitId, that.visitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId);
    }
}

package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "patient_visits_history")
@Getter
@Setter
@NoArgsConstructor
public class PatientVisitHistory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "visit_id")
    private UUID visitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
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

package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Represents the history of a patient's visits.")
public class PatientVisitHistory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "visit_id")
    @Schema(description = "Unique identifier for the visit.", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID visitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @Schema(description = "Patient who attended the visit.")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id")
    @Schema(description = "Specialist who conducted the visit.")
    private Specialist specialist;

    @Column(name = "visit_type")
    @Schema(description = "Type of the visit, such as 'Consultation' or 'Follow-up'.", example = "Consultation")
    private String visitType;

    @Column(name = "patient_condition")
    @Schema(description = "Condition of the patient during the visit.", example = "Stable")
    private String patientCondition;

    @Column(name = "visit_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Date and time of the visit.", example = "2024-01-01T10:00:00")
    private LocalDateTime visitDateTime;

    @Column(name = "purpose")
    @Schema(description = "Purpose of the visit.", example = "Routine check-up")
    private String purpose;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    @Schema(description = "Medical record associated with this visit.")
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

    @Override
    public String toString() {
        return "PatientVisitHistory{" +
                "visitId=" + visitId +
                ", patient=" + patient +
                ", specialist=" + specialist +
                ", visitType='" + visitType + '\'' +
                ", patientCondition='" + patientCondition + '\'' +
                ", visitDateTime=" + visitDateTime +
                ", purpose='" + purpose + '\'' +
                ", medicalRecord=" + medicalRecord +
                '}';
    }
}

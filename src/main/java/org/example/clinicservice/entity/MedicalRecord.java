package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a medical record associated with a patient.")
public class MedicalRecord {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "medical_record_id")
    @Schema(description = "Unique identifier for the medical record.", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @Schema(description = "Patient associated with the medical record.")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id")
    @Schema(description = "Doctor responsible for the medical record.")
    private Specialist doctor;

    @Column(name = "diagnoses")
    @Schema(description = "Diagnoses made by the doctor.", example = "Hypertension")
    private String diagnose;

    @Column(name = "doctor_conclusion")
    @Schema(description = "Doctor's conclusion after the examination.", example = "Patient requires regular check-ups.")
    private String doctorConclusion;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    @Schema(description = "Prescriptions associated with the medical record.")
    private List<Prescription> prescriptions = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "medical_procedures", joinColumns = @JoinColumn(name = "record_id"))
    @Column(name = "procedures")
    @Schema(description = "List of medical procedures associated with the record.", example = "X-ray, Blood test")
    private List<String> medicalProcedure;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    @Schema(description = "Lab reports associated with the medical record.")
    private List<LabReport> labReports = new ArrayList<>();

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

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId=" + recordId +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", diagnose='" + diagnose + '\'' +
                ", doctorConclusion='" + doctorConclusion + '\'' +
                ", prescription='" + prescriptions + '\'' +
                ", medicalProcedure=" + medicalProcedure +
                ", labReports=" + labReports +
                '}';
    }
}

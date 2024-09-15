package org.example.clinicservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a medical prescription for a patient.")
public class Prescription {

    /**
     * Unique identifier for the prescription.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "prescription_id")
    @Schema(description = "Unique identifier for the prescription.")
    private UUID prescriptionId;


    /**
     * Name of the prescribed medication.
     */
    @Column(name = "medication_name")
    @NotNull
    @Schema(description = "Name of the prescribed medication.", example = "Paracetamol")
    private String medicationName;

    /**
     * Date when the prescription was issued.
     */
    @Column(name = "prescription_date")
    @Schema(description = "Date when the prescription was issued.", example = "2024-01-01T10:00:00")
    private LocalDateTime prescriptionDate;

    /**
     * Dosage instructions for the medication.
     */
    @Column(name = "dosage")
    @NotNull
    @Schema(description = "Dosage instructions for the medication.", example = "500 mg")
    private String dosage;

    /**
     * Instructions for administering the medication.
     */
    @Column(name = "instructions")
    @NotNull
    @Schema(description = "Instructions for administering the medication.", example = "Take one tablet twice daily after meals.")
    private String instructions;

    /**
     * Doctor who issued the prescription.
     */
    @ManyToOne
    @JoinColumn(name = "specialist_id")
    @Schema(description = "Doctor who issued the prescription.")
    private Specialist doctor;

    /**
     * Patient for whom the prescription was issued.
     */
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @Schema(description = "Patient for whom the prescription was issued.")
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(prescriptionId, that.prescriptionId) &&
                Objects.equals(prescriptionDate, that.prescriptionDate) &&
                Objects.equals(medicationName, that.medicationName) &&
                Objects.equals(dosage, that.dosage) &&
                Objects.equals(instructions, that.instructions) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, prescriptionDate, medicationName, dosage, instructions, doctor, patient);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", prescriptionDate=" + prescriptionDate +
                ", medicationName='" + medicationName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", instructions='" + instructions + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}

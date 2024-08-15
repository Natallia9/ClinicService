package org.example.clinicservice.entity;

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
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "prescription_Id",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID prescriptionId;

    @Column(name = "medication_name")
    @NotNull
    private String medicationName;

    @Column(name = "prescription_date")
    private LocalDateTime prescriptionDate;

    @Column(name = "dosage")
    @NotNull
    private String dosage;

    @Column(name = "instructions")
    @NotNull
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Specialist doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(prescriptionId, that.prescriptionId) && Objects.equals(prescriptionDate, that.prescriptionDate) && Objects.equals(medicationName, that.medicationName) && Objects.equals(dosage, that.dosage) && Objects.equals(instructions, that.instructions) && Objects.equals(doctor, that.doctor) && Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, prescriptionDate, medicationName, dosage, instructions, doctor, patient);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + prescriptionId +
                ", prescriptionDate=" + prescriptionDate +
                ", medicationName='" + medicationName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", instructions='" + instructions + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}

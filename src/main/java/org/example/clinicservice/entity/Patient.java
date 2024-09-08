package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a patient in the healthcare system.")
public class Patient extends User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "patient_id")
    @Schema(description = "Unique identifier for the patient.", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID patientId;

    @Column(name = "phone_number", nullable = false)
    @Schema(description = "Contact phone number of the patient.", example = "+1234567890")
    private String phoneNumber;

    @Column(name = "age")
    @Schema(description = "Age of the patient.", example = "30")
    private String age;

    @Column(name = "sex")
    @Schema(description = "Gender of the patient.", example = "Male")
    private String sex;

    @Column(name = "address")
    @Schema(description = "Home address of the patient.", example = "123 Main St, City")
    private String address;

    @Column(name = "emergency_contact")
    @Schema(description = "Emergency contact information for the patient.", example = "Jane Doe, +0987654321")
    private String emergencyContact;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "patient_specialist",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "specialist_id")
    )
    @Schema(description = "Specialists associated with the patient.")
    private List<Specialist> specialists;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = MedicalRecord.class)
    @JsonManagedReference
    @Schema(description = "List of medical records associated with the patient.")
    private List<MedicalRecord> medicalRecords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(patientId, patient.patientId) && Objects.equals(phoneNumber, patient.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, phoneNumber);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", specialists=" + specialists +
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}

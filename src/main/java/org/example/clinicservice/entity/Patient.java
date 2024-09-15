package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("PATIENT")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a patient in the healthcare system.")
public class Patient extends User {

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

    @ManyToMany
    @JoinTable(
            name = "patient_specialist",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "specialist_id")
    )
    @Schema(description = "Specialists associated with the patient.")
    private List<Specialist> specialists = new ArrayList<>();


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
        return Objects.equals(phoneNumber, patient.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                '}';
    }
}

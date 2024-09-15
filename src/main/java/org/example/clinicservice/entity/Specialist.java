package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("SPECIALIST")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a medical specialist in the system.")
public class Specialist extends User {

    @Column(name = "area_of_specialization")
    @Schema(description = "Area of specialization of the specialist", example = "Cardiology")
    private String areaOfSpecialization;

    @Column(name = "experience")
    @Schema(description = "Experience of the specialist in years", example = "10 years")
    private String experience;

    @Column(name = "contact_information")
    @Schema(description = "Contact information of the specialist", example = "+1234567890")
    private String contactInformation;

    @Column(name = "availability")
    @Schema(description = "Indicates whether the specialist is currently available")
    private boolean availability;

    @Column(name = "languages")
    @Schema(description = "Languages spoken by the specialist", example = "English, Spanish")
    private String languagesSpoken;

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    @Schema(description = "Department of the specialist", example = "Cardiology")
    private Department department;

    @JsonBackReference
    @OneToMany(mappedBy = "doctor")
    @Schema(description = "List of appointments associated with the specialist")
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany(mappedBy = "specialists")
    @Schema(description = "List of patients associated with the specialist")
    private List<Patient> patients = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist that = (Specialist) o;
        return Objects.equals(areaOfSpecialization, that.areaOfSpecialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaOfSpecialization);
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "areaOfSpecialization='" + areaOfSpecialization + '\'' +
                ", experience='" + experience + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", availability=" + availability +
                ", languagesSpoken='" + languagesSpoken + '\'' +
                ", department=" + department +
                '}';
    }
}

package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.Department;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "specialists")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a medical specialist in the system.")
public class Specialist extends User {

    /**
     * Unique identifier of the specialist.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "specialist_id")
    @Schema(description = "Unique identifier of the specialist")
    private UUID specialistId;

    /**
     * Area of specialization of the specialist.
     */
    @Column(name = "area_of_specialization")
    @Schema(description = "Area of specialization of the specialist", example = "Cardiology")
    private String areaOfSpecialization;

    /**
     * Experience of the specialist.
     */
    @Column(name = "experience")
    @Schema(description = "Experience of the specialist in years", example = "10 years")
    private String experience;

    /**
     * Contact information of the specialist.
     */
    @Column(name = "contact_information")
    @Schema(description = "Contact information of the specialist", example = "+1234567890")
    private String contactInformation;

    /**
     * Availability of the specialist.
     */
    @Column(name = "availability")
    @Schema(description = "Indicates whether the specialist is currently available")
    private boolean availability;

    /**
     * Languages spoken by the specialist.
     */
    @Column(name = "languages")
    @Schema(description = "Languages spoken by the specialist", example = "English, Spanish")
    private String languagesSpoken;

    /**
     * Department the specialist belongs to.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    @Schema(description = "Department of the specialist", example = "Cardiology")
    private Department department;

    /**
     * Appointments associated with the specialist.
     */
    @JsonBackReference
    @OneToMany(mappedBy = "doctor")
    @Schema(description = "List of appointments associated with the specialist")
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Patients associated with the specialist.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "specialists")
    @Schema(description = "List of patients associated with the specialist")
    private List<Patient> patients = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist that = (Specialist) o;
        return Objects.equals(specialistId, that.specialistId) && Objects.equals(areaOfSpecialization, that.areaOfSpecialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialistId, areaOfSpecialization);
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "specialistId=" + specialistId +
                ", areaOfSpecialization='" + areaOfSpecialization + '\'' +
                ", experience='" + experience + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", availability=" + availability +
                ", languagesSpoken='" + languagesSpoken + '\'' +
                ", department=" + department +
                ", appointments=" + appointments +
                ", patients=" + patients +
                '}';
    }
}

package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.Department;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "specialists")
@Getter
@Setter
@NoArgsConstructor
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "specialist_id")
    private UUID specialistId;

    @Column(name = "field")
    private String field;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "experience")
    private String experience;

    @Temporal(TemporalType.TIMESTAMP)
    private Date workingHours;

    @Column(name = "contact_inform")
    private String contactInformation;

    @Column(name = "availability")
    private boolean	availability;

    @Column(name = "languages")
    private String languagesSpoken;

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToMany
    private List<Appointment> appointments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist that = (Specialist) o;
        return Objects.equals(specialistId, that.specialistId) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialistId, field);
    }
}

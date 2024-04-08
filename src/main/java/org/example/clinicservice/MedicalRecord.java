package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medical_record_id")
    private UUID recordId;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Specialist doctor;

    @Column(name = "diagnoses")
    private String diagnose;

    @Column(name = "doctor_conclusion")
    private String doctorConclusion;

    @Column(name = "prescription")
    private String prescription;

    @ElementCollection
    @CollectionTable(name = "medical_procedures", joinColumns = @JoinColumn(name = "record_id"))
    @Column(name = "procedures")
    private List<String> medicalProcedure;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private List<LabReport> labReports;

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
}

package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "lab_reports")
@Getter
@Setter
@NoArgsConstructor
public class LabReport {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "report_id")
    private UUID reportId;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "report_content")
    private String reportContent;

    @Column(name = "report_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime reportDate;

    @Column(name = "result")
    private String results;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabReport labReport = (LabReport) o;
        return Objects.equals(reportId, labReport.reportId) && Objects.equals(reportName, labReport.reportName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, reportName);
    }
}

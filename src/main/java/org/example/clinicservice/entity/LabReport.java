package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Represents a lab report associated with a medical record.")
public class LabReport {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "report_id")
    @Schema(description = "Unique identifier for the lab report.", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID reportId;

    @Column(name = "report_name")
    @Schema(description = "Name of the lab report.", example = "Blood Test")
    private String reportName;

    @Column(name = "report_content")
    @Schema(description = "Content or findings of the lab report.", example = "High cholesterol levels detected.")
    private String reportContent;

    @Column(name = "report_date")
    @Schema(description = "Date and time when the lab report was created.", example = "2024-09-07T10:30:00")
    private LocalDateTime reportDate;


    @Column(name = "result")
    @Schema(description = "Results or conclusions of the lab test.", example = "Normal")
    private String results;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    @Schema(description = "Associated medical record for this lab report.")
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

    @Override
    public String toString() {
        return "LabReport{" +
                "reportId=" + reportId +
                ", reportName='" + reportName + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", reportDate=" + reportDate +
                ", results='" + results + '\'' +
                ", medicalRecord=" + medicalRecord +
                '}';
    }
}

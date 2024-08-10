package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.LabReport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LabReportService {

    LabReport getLabReportById(UUID reportId);
    List<LabReport> getLabReportsByName(String reportName);
    List<LabReport> getLabReportsByDate(LocalDateTime reportDate);
    List<LabReport> getLabReportsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    List<LabReport> getLabReportsByMedicalRecordId(UUID medicalRecordId);
    void saveLabReport(LabReport labReport);
    void deleteLabReport(UUID reportId);
}

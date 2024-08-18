package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.entity.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LabReportService {

    LabReport getLabReportById(UUID reportId);
    Map<MedicalRecord, List<LabReport>> getLabReportsByDate(LocalDateTime reportDate);
    void saveLabReport(LabReport labReport);
    void deleteLabReport(UUID reportId);
}

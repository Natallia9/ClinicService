package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.labReportExceptions.LabReportNotFoundException;
import org.example.clinicservice.exceptions.labReportExceptions.LabReportsNotFoundException;
import org.example.clinicservice.repository.LabReportRepository;
import org.example.clinicservice.service.interfeces.LabReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabReportServiceImpl implements LabReportService {

    private final LabReportRepository labReportRepository;

    @Override
    public LabReport getLabReportById(UUID reportId) {
        return labReportRepository.findById(reportId)
                .orElseThrow(() -> new LabReportNotFoundException(ErrorMessage.LAB_REPORT_NOT_FOUND));
    }

    @Override
    public Map<MedicalRecord, List<LabReport>> getLabReportsByDate(LocalDateTime reportDate) {
        List<LabReport> labReports = labReportRepository.findByReportDate(reportDate);
        if (labReports.isEmpty()) {
            throw new LabReportsNotFoundException(ErrorMessage.LAB_REPORTS_NOT_FOUND);
        }
        return labReports.stream()
                .collect(Collectors.groupingBy(LabReport::getMedicalRecord));
    }

    @Override
    public void saveLabReport(LabReport labReport) {
        labReportRepository.save(labReport);
    }

    @Override
    public void deleteLabReport(UUID reportId) {
        getLabReportById(reportId);
        labReportRepository.deleteById(reportId);
    }
}

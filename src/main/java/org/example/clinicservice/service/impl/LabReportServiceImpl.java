package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.labReportExceptions.LabReportAlreadyExistsException;
import org.example.clinicservice.exceptions.labReportExceptions.LabReportNotFoundException;
import org.example.clinicservice.exceptions.labReportExceptions.LabReportSaveException;
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

        try {
            LabReport labReport = labReportRepository.findByReportId(reportId);
            if(reportId == null){
                throw new LabReportNotFoundException(ErrorMessage.LAB_REPORT_NOT_FOUND);
            }
            return labReport;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving lab report with id " + reportId, e);
        }
    }

    @Override
    public Map<MedicalRecord, List<LabReport>> getLabReportsByDate(LocalDateTime reportDate) {

        try {
            List<LabReport> labReports = labReportRepository.findByReportDate(reportDate);
            if (labReports.isEmpty()) {
                throw new LabReportsNotFoundException(ErrorMessage.LAB_REPORTS_NOT_FOUND);
            }
            Map<MedicalRecord, List<LabReport>> patientLabReports = labReports.stream()
                    .collect(Collectors.groupingBy(LabReport::getMedicalRecord));

            return patientLabReports;

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve lab reports for date: " + reportDate, e);
        }
    }

    @Override
    public void saveLabReport(LabReport labReport) {

        if (labReportRepository.existsById(labReport.getReportId())){
            throw new LabReportAlreadyExistsException(ErrorMessage.LAB_REPORT_ALREADY_EXISTS);
        }
        try {
            labReportRepository.save(labReport);
        } catch (Exception e) {
            throw new LabReportSaveException("Failed to save lab report due to an unexpected error: " + e.getMessage());
        }
    }

    @Override
    public void deleteLabReport(UUID reportId) {

        if (!labReportRepository.existsById(reportId)) {
            throw new LabReportNotFoundException(ErrorMessage.LAB_REPORT_NOT_FOUND);
        }

        try {
            labReportRepository.deleteById(reportId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete lab report with ID: " + reportId, e);
        }
    }
}

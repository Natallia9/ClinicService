package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.repository.LabReportRepository;
import org.example.clinicservice.service.interfeces.LabReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LabReportServiceImpl implements LabReportService {

    private final LabReportRepository labReportRepository;

    @Override
    public LabReport getLabReportById(UUID reportId) {

        return labReportRepository.findByReportId(reportId);
    }

    @Override
    public List<LabReport> getLabReportsByName(String reportName) {

        return labReportRepository.findByReportName(reportName);
    }

    @Override
    public List<LabReport> getLabReportsByDate(LocalDateTime reportDate) {

        return labReportRepository.findByReportDate(reportDate);
    }

    @Override
    public List<LabReport> getLabReportsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {

        return labReportRepository.findByReportDateBetween(startDate, endDate);
    }



    @Override
    public List<LabReport> getLabReportsByMedicalRecordId(UUID medicalRecordId) {

        return labReportRepository.findByMedicalRecordId(medicalRecordId);
    }

    @Override
    public void saveLabReport(LabReport labReport) {
        labReportRepository.save(labReport);
    }

    @Override
    public void deleteLabReport(UUID reportId) {
        labReportRepository.deleteById(reportId);
    }
}

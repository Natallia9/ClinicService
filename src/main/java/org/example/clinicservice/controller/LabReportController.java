package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.LabReportDTO;
import org.example.clinicservice.dto.MedicalRecordDTO;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.service.interfeces.LabReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lab-reports")
@RequiredArgsConstructor
public class LabReportController {

    private final LabReportService labReportService;
    private final LabReportTransformer transformer;

    @GetMapping("/{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public LabReportDTO getLabReportById(@PathVariable UUID reportId) {
        LabReport labReport = labReportService.getLabReportById(reportId);
        return transformer.convertToDTO(labReport);
    }

    @GetMapping("/date/{reportDate}")
    @ResponseStatus(HttpStatus.OK)
    public Map<MedicalRecordDTO, List<LabReportDTO>> getLabReportsByDate(@PathVariable LocalDateTime reportDate) {
        Map<MedicalRecord, List<LabReport>> labReportsByRecord = labReportService.getLabReportsByDate(reportDate);
        return labReportsByRecord.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> transformer.convertMedicalRecordToDTO(entry.getKey()),
                        entry -> entry.getValue().stream()
                                .map(transformer::convertToDTO)
                                .collect(Collectors.toList())
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabReportDTO saveLabReport(@RequestBody LabReportDTO labReportDTO) {
        LabReport labReport = transformer.convertToEntity(labReportDTO);
        labReportService.saveLabReport(labReport);
        return transformer.convertToDTO(labReport);
    }

    @DeleteMapping("/{reportId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabReport(@PathVariable UUID reportId) {
        labReportService.deleteLabReport(reportId);
    }
}


package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.LabReportDTO;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.service.interfeces.MedicalRecordService;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface LabReportMapper {

    @Mappings({
            @Mapping(target = "reportName", source = "reportName"),
            @Mapping(target = "reportContent", source = "reportContent"),
            @Mapping(target = "reportDate", source = "reportDate"),
            @Mapping(target = "results", source = "results"),
            @Mapping(target = "medicalRecord", source = "medicalRecord")
    })
    LabReport toEntity(LabReportDTO labReportDTO);

    @AfterMapping
    default void createdLabReport(
            @MappingTarget LabReport labReport,
            LabReportDTO labReportDTO,
            @Context MedicalRecordService medicalRecordService) {

        labReport.setReportId(UUID.randomUUID());
        labReport.setReportName(labReportDTO.getReportName());
        labReport.setReportContent(labReportDTO.getReportContent());
        labReport.setReportDate(labReportDTO.getReportDate());
        labReport.setResults(labReportDTO.getResults());

        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(labReportDTO.getMedicalRecord().getRecordId());
        if (medicalRecord != null) {
            labReport.setMedicalRecord(medicalRecord);

        }
    }

    @Mappings({
            @Mapping(target = "reportName", source = "reportName"),
            @Mapping(target = "reportContent", source = "reportContent"),
            @Mapping(target = "reportDate", source = "reportDate"),
            @Mapping(target = "results", source = "results"),
            @Mapping(target = "medicalRecord", source = "medicalRecord")
    })
    LabReportDTO toDto(LabReport labReport);
}

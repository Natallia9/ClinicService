package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.MedicalRecordDTO;
import org.example.clinicservice.entity.*;
import org.example.clinicservice.service.interfeces.LabReportService;
import org.example.clinicservice.service.interfeces.PrescriptionService;
import org.example.clinicservice.service.interfeces.UserService;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface MedicalRecordMapper {

    @Mappings({
            @Mapping(target = "recordId", ignore = true),
            @Mapping(target = "patient", ignore = true, qualifiedByName = "patientFromId"),
            @Mapping(target = "doctor", ignore = true, qualifiedByName = "doctorFromId"),
            @Mapping(target = "prescriptions", ignore = true, qualifiedByName = "prescriptionsFromIds"),
            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
            @Mapping(target = "labReports", ignore = true, qualifiedByName = "labReportsFromIds")
    })
    MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO);

    @Mappings({
            @Mapping(target = "recordId", source = "recordId"),
            @Mapping(target = "patientId", source = "patient.userId"),
            @Mapping(target = "doctorId", source = "doctor.userId"),
            @Mapping(target = "prescriptionIds", source = "prescriptions", qualifiedByName = "prescriptionIdsFromPrescriptions"),
            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
            @Mapping(target = "labReportIds", source = "labReports", qualifiedByName = "labReportIdsFromLabReports")
    })
    MedicalRecordDTO toDto(MedicalRecord medicalRecord);

    @Named("patientFromId")
    default Patient patientFromId(UUID patientId, @Context UserService userService) {
        User user = userService.getUserById(patientId);
        if (user instanceof Patient) {
            return (Patient) user;
        } else {
            throw new IllegalArgumentException(patientId + " is not a Patient");
        }
    }

    @Named("doctorFromId")
    default Specialist doctorFromId(UUID doctorId, @Context UserService userService) {
        User user = userService.getUserById(doctorId);
        if (user instanceof Specialist) {
            return (Specialist) user;
        } else {
            throw new IllegalArgumentException(doctorId + " is not a Specialist");
        }
    }

    @Named("prescriptionsFromIds")
    default List<Prescription> prescriptionsFromIds(List<UUID> prescriptionIds, @Context PrescriptionService prescriptionService) {
        return prescriptionIds.stream()
                .map(prescriptionService::getPrescriptionById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Named("prescriptionIdsFromPrescriptions")
    default List<UUID> prescriptionIdsFromPrescriptions(List<Prescription> prescriptions) {
        return prescriptions.stream()
                .map(Prescription::getPrescriptionId)
                .collect(Collectors.toList());
    }

    @Named("labReportsFromIds")
    default List<LabReport> labReportsFromIds(List<UUID> labReportIds, @Context LabReportService labReportService) {
        return labReportIds.stream()
                .map(labReportService::getLabReportById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Named("labReportIdsFromLabReports")
    default List<UUID> labReportIdsFromLabReports(List<LabReport> labReports) {
        return labReports.stream()
                .map(LabReport::getReportId)
                .collect(Collectors.toList());
    }
}

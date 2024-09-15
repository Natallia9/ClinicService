package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.PatientVisitHistoryDTO;
import org.example.clinicservice.entity.*;
import org.example.clinicservice.service.interfeces.MedicalRecordService;
import org.example.clinicservice.service.interfeces.UserService;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface PatientVisitHistoryMapper {

    @Mappings({
            @Mapping(target = "visitId", ignore = true),
            @Mapping(target = "patient", ignore = true, qualifiedByName = "patientFromId"),
            @Mapping(target = "specialist", ignore = true, qualifiedByName = "specialistFromId"),
            @Mapping(target = "visitType", source = "visitType"),
            @Mapping(target = "patientCondition", source = "patientCondition"),
            @Mapping(target = "visitDateTime", source = "visitDateTime"),
            @Mapping(target = "purpose", source = "purpose"),
            @Mapping(target = "medicalRecord", ignore = true, qualifiedByName = "medicalRecordFromId")
    })
    PatientVisitHistory toEntity(PatientVisitHistoryDTO patientVisitHistoryDTO);

    @Mappings({
            @Mapping(target = "visitId", source = "visitId"),
            @Mapping(target = "patientId", source = "patient.userId"),
            @Mapping(target = "specialistId", source = "specialist.userId"),
            @Mapping(target = "visitType", source = "visitType"),
            @Mapping(target = "patientCondition", source = "patientCondition"),
            @Mapping(target = "visitDateTime", source = "visitDateTime"),
            @Mapping(target = "purpose", source = "purpose"),
            @Mapping(target = "medicalRecordId", source = "medicalRecord.recordId")
    })
    PatientVisitHistoryDTO toDto(PatientVisitHistory patientVisitHistory);

    @Named("patientFromId")
    default Patient patientFromId(UUID patientId, @Context UserService userService) {
        User user = userService.getUserById(patientId);
        if (user instanceof Patient) {
            return (Patient) user;
        } else {
            throw new IllegalArgumentException(patientId + " is not a Patient");
        }
    }

    @Named("specialistFromId")
    default Specialist specialistFromId(UUID specialistId, @Context UserService userService) {
        User user = userService.getUserById(specialistId);
        if (user instanceof Specialist) {
            return (Specialist) user;
        } else {
            throw new IllegalArgumentException(specialistId + " is not a Specialist");
        }
    }

    @Named("medicalRecordFromId")
    default MedicalRecord medicalRecordFromId(UUID medicalRecordId, @Context MedicalRecordService medicalRecordService) {
        return medicalRecordService.getMedicalRecordById(medicalRecordId);
    }
}

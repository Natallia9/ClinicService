package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.PatientVisitHistoryDTO;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientVisitHistoryMapper {

    @Mappings({
            @Mapping(target = "visitId", ignore = true),
            @Mapping(target = "patientId", ignore = true),
            @Mapping(target = "specialistId", ignore = true),
            @Mapping(target = "visitType", source = "visitType"),
            @Mapping(target = "patientCondition", source = "patientCondition"),
            @Mapping(target = "visitDateTime", source = "visitDateTime"),
            @Mapping(target = "purpose", source = "purpose"),
            @Mapping(target = "medicalRecordId", ignore = true)
    })
    PatientVisitHistory toEntity(PatientVisitHistoryDTO patientVisitHistoryDTO);

    @Mapping(target = "visitId", ignore = true)
    PatientVisitHistoryDTO toDTO(PatientVisitHistory patientVisitHistory);
}

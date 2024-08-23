package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.entity.Patient;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    @Mappings({
            @Mapping(target = "phoneNumber", source = "phoneNumber"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "sex", source = "sex"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "emergencyContact", source = "emergencyContact")
    })
    Patient toEntity(PatientDTO patientDTO);

    @Mappings({
            @Mapping(target = "phoneNumber", source = "phoneNumber"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "sex", source = "sex"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "emergencyContact", source = "emergencyContact")
    })
    PatientDTO toDto(Patient patient);
}

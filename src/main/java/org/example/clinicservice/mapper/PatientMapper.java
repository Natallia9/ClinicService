package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "emergencyContact", target = "emergencyContact")
    PatientAfterCreationDTO toPatientAfterCreationDTO(Patient patient);

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "emergencyContact", target = "emergencyContact")
    PatientDTO toPatientDTO(Patient patient);

    Patient toEntity(PatientAfterCreationDTO patientDTO);

    PatientDTO toDTO(Patient patient); // Добавленный метод для преобразования Patient в PatientDTO
}

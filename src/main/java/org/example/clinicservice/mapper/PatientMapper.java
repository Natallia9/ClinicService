package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.entity.Patient;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "phoneNumber", source = "phoneNumber"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "sex", source = "sex"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "emergencyContact", source = "emergencyContact")
    })
    Patient toEntity(PatientDTO patientDTO);

    @AfterMapping
    default void createdPatient(@MappingTarget Patient patient, PatientDTO patientDTO) {
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setAge(patientDTO.getAge());
        patient.setSex(patientDTO.getSex());
        patient.setAddress(patientDTO.getAddress());
        patient.setEmergencyContact(patientDTO.getEmergencyContact());
    }

    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "phoneNumber", source = "phoneNumber"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "sex", source = "sex"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "emergencyContact", source = "emergencyContact")
    })
    PatientDTO toDto(Patient patient);
}

package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

import static org.example.clinicservice.mapper.UserMapper.passwordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SpecialistMapper {

    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "areaOfSpecialization", source = "areaOfSpecialization"),
            @Mapping(target = "experience", source = "experience"),
            @Mapping(target = "contactInformation", source = "contactInformation"),
            @Mapping(target = "availability", source = "availability"),
            @Mapping(target = "languagesSpoken", source = "languagesSpoken"),
            @Mapping(target = "department", source = "department"),
            @Mapping(target = "appointments", source = "appointments")
    })
    Specialist toEntity(SpecialistDTO specialistDTO);

    @AfterMapping
    default void createdSpecialist(@MappingTarget Specialist specialist, SpecialistDTO specialistDTO){

        specialist.setAreaOfSpecialization(specialistDTO.getAreaOfSpecialization());
        specialist.setExperience(specialistDTO.getExperience());
        specialist.setContactInformation(specialistDTO.getContactInformation());
        specialist.setAvailability(specialistDTO.isAvailability());
        specialist.setLanguagesSpoken(specialistDTO.getLanguagesSpoken());
        specialist.setDepartment(specialistDTO.getDepartment());
        specialist.setAppointments(specialistDTO.getAppointments());
    }

    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "areaOfSpecialization", source = "areaOfSpecialization"),
            @Mapping(target = "experience", source = "experience"),
            @Mapping(target = "contactInformation", source = "contactInformation"),
            @Mapping(target = "availability", source = "availability"),
            @Mapping(target = "languagesSpoken", source = "languagesSpoken"),
            @Mapping(target = "department", source = "department"),
            @Mapping(target = "appointments", source = "appointments")
    })
    SpecialistDTO toDto(Specialist specialist);

}

package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Specialist;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
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
    SpecialistDTO toDto(Optional<Specialist> specialist);
}

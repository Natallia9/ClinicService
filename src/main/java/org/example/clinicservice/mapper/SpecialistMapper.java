package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Specialist;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SpecialistMapper {


    @Mappings({
            @Mapping(target = "areaOfSpecialization", source = "areaOfSpecialization"),
            @Mapping(target = "experience", source = "experience"),
            @Mapping(target = "contactInformation", source = "contactInformation"),
            @Mapping(target = "availability", source = "availability"),
            @Mapping(target = "languagesSpoken", source = "languagesSpoken"),
            @Mapping(target = "department", source = "department"),
            @Mapping(target = "appointments", source = "appointments")
    })
    Specialist toEntity(SpecialistDTO specialistDTO);

    @Mappings({
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

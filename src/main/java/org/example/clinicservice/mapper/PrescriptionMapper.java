package org.example.clinicservice.mapper;

import lombok.NoArgsConstructor;
import org.example.clinicservice.dto.PrescriptionDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.example.clinicservice.service.interfeces.UserService;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface PrescriptionMapper {

    @Mappings({
            @Mapping(target = "prescriptionId", ignore = true),
            @Mapping(target = "medicationName", source = "medicationName"),
            @Mapping(target = "dosage", source = "dosage"),
            @Mapping(target = "instructions", source = "instructions"),
            @Mapping(target = "doctor", source = "doctor"),
            @Mapping(target = "patient", source = "patient")
    })
    Prescription toEntity(PrescriptionDTO dto);

    @AfterMapping
    default void createdPrescription(@MappingTarget Prescription prescription,
                                     PrescriptionDTO prescriptionDTO){

        prescription.setPrescriptionDate(LocalDateTime.now());
        prescription.setPrescriptionId(UUID.randomUUID());
        prescription.setMedicationName(prescriptionDTO.getMedicationName());
        prescription.setDosage(prescriptionDTO.getDosage());
        prescription.setInstructions(prescriptionDTO.getInstructions());

        prescription.setDoctor(prescriptionDTO.getDoctor());
        prescription.setPatient(prescriptionDTO.getPatient());

    }


    @Mapping(target = "prescriptionId", ignore = true)
    PrescriptionDTO toDto(Prescription entity);

}

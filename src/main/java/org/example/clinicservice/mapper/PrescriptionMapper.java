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
            @Mapping(target = "doctorId", ignore = true),
            @Mapping(target = "patientId", ignore = true)
    })
    Prescription toEntity(PrescriptionDTO dto);

    @AfterMapping
    default void createdPrescription(@MappingTarget Prescription prescription,
                                     PrescriptionDTO prescriptionDTO,
                                     @Context UserService userService){

        prescription.setPrescriptionDate(LocalDateTime.now());
        prescription.setPrescriptionId(UUID.randomUUID());
        prescription.setMedicationName(prescriptionDTO.getMedicationName());
        prescription.setDosage(prescriptionDTO.getDosage());
        prescription.setInstructions(prescriptionDTO.getInstructions());

        User specialist = userService.getUserById(prescriptionDTO.getDoctorId());
        if(specialist instanceof Specialist){
            prescription.setDoctor((Specialist) specialist);
        }

        User patient = userService.getUserById(prescriptionDTO.getPatientId());
        if (patient instanceof Patient) {
            prescription.setPatient((Patient) patient);
        }

    }


    @Mapping(target = "prescriptionId", ignore = true)
    PrescriptionDTO toDto(Prescription entity);

}

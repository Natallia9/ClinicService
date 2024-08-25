package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.PatientVisitHistoryDTO;
import org.example.clinicservice.entity.*;
import org.example.clinicservice.service.interfeces.MedicalRecordService;
import org.example.clinicservice.service.interfeces.PatientService;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.example.clinicservice.service.interfeces.UserService;
import org.mapstruct.*;

import java.util.UUID;

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
            @Mapping(target = "medicalRecord", source = "medicalRecord")
    })
    PatientVisitHistory toEntity(PatientVisitHistoryDTO patientVisitHistoryDTO);

    @AfterMapping
    default void createdPatientVisitHistory(
            @MappingTarget PatientVisitHistory patientVisitHistory,
            PatientVisitHistoryDTO patientVisitHistoryDTO,
            @Context UserService userService,
            @Context MedicalRecordService medicalRecordService) {


        patientVisitHistory.setVisitId(UUID.randomUUID());

        User patient = userService.getUserById(patientVisitHistoryDTO.getPatientId());
        if (patient instanceof Patient) {
            patientVisitHistory.setPatient((Patient) patient);
        }

        User specialist = userService.getUserById(patientVisitHistoryDTO.getSpecialistId());
        if(specialist instanceof Specialist){
            patientVisitHistory.setSpecialist((Specialist) specialist);
        }

        patientVisitHistory.setVisitType(patientVisitHistoryDTO.getVisitType());
        patientVisitHistory.setPatientCondition(patientVisitHistoryDTO.getPatientCondition());
        patientVisitHistory.setVisitDateTime(patientVisitHistoryDTO.getVisitDateTime());
        patientVisitHistory.setPurpose(patientVisitHistoryDTO.getPurpose());

        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(patientVisitHistoryDTO.getMedicalRecordId());
        patientVisitHistory.setMedicalRecord(medicalRecord);

    }

    @Mapping(target = "visitId", ignore = true)
    PatientVisitHistoryDTO toDto(PatientVisitHistory patientVisitHistory);
}

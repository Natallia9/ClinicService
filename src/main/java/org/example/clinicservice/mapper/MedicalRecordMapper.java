package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.MedicalRecordDTO;
import org.example.clinicservice.entity.*;
import org.example.clinicservice.service.interfeces.LabReportService;
import org.example.clinicservice.service.interfeces.PrescriptionService;
import org.example.clinicservice.service.interfeces.UserService;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface MedicalRecordMapper {
    @Mappings({
            @Mapping(target = "patient", source = "patient"),
            @Mapping(target = "doctor", source = "doctor"),
            @Mapping(target = "diagnose", source = "diagnose"),
            @Mapping(target = "doctorConclusion", source = "doctorConclusion"),
            @Mapping(target = "prescription", source = "prescription"),
            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
            @Mapping(target = "labReports", source = "labReports"),
    })
    MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO);

    @AfterMapping
    default void createdMedicalRecord(
            @MappingTarget MedicalRecord medicalRecord,
            MedicalRecordDTO medicalRecordDTO,
            @Context UserService userService,
            @Context PrescriptionService prescriptionService,
            @Context LabReportService labReportService) {

        medicalRecord.setRecordId(UUID.randomUUID());

        User patient = userService.getUserById(medicalRecordDTO.getPatient().getUserId());
        if (patient instanceof Patient) {
            medicalRecord.setPatient((Patient) patient);
        }

        User specialist = userService.getUserById(medicalRecordDTO.getDoctor().getUserId());
        if (specialist instanceof Specialist) {
            medicalRecord.setDoctor((Specialist) specialist);
        }

        medicalRecord.setDiagnose(medicalRecordDTO.getDiagnose());
        medicalRecord.setDoctorConclusion(medicalRecordDTO.getDoctorConclusion());

        Prescription prescription = prescriptionService.getPrescriptionById(medicalRecordDTO.getPrescription().getPrescriptionId());
        prescription.getPrescriptionId();

        List<String> medicalProcedures = new ArrayList<>();
        for (String medProcedure : medicalRecordDTO.getMedicalProcedure()){
            if (medProcedure.isEmpty()){
                medicalProcedures.add(medProcedure);

            }
        }
        medicalRecord.setMedicalProcedure(medicalProcedures);

        List<LabReport> labReports = new ArrayList<>();
        for (LabReport labReport : medicalRecordDTO.getLabReports()) {
            LabReport report = labReportService.getLabReportById(labReport.getReportId());
            if (report != null) {
                labReports.add(report);
            }
        }
        medicalRecord.setLabReports(labReports);
    }


    @Mappings({
            @Mapping(target = "patient", source = "patient"),
            @Mapping(target = "doctor", source = "doctor"),
            @Mapping(target = "diagnose", source = "diagnose"),
            @Mapping(target = "doctorConclusion", source = "doctorConclusion"),
            @Mapping(target = "prescription", source = "prescription"),
            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
            @Mapping(target = "labReports", source = "labReports"),
    })
    MedicalRecordDTO toDto(MedicalRecord medicalRecord);
}

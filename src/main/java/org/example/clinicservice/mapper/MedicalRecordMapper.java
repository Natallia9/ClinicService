package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.MedicalRecordDTO;
import org.example.clinicservice.entity.*;
import org.example.clinicservice.service.interfeces.LabReportService;
import org.example.clinicservice.service.interfeces.PrescriptionService;
import org.example.clinicservice.service.interfeces.UserService;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalRecordMapper {

    @Mappings({
            @Mapping(target = "patient", source = "patientId"),
            @Mapping(target = "doctor", source = "doctorId"),
            @Mapping(target = "diagnose", source = "diagnose"),
            @Mapping(target = "doctorConclusion", source = "doctorConclusion"),
            @Mapping(target = "prescription", source = "prescriptionId"),
            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
            @Mapping(target = "labReports", source = "labReportIds"),
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

        User patient = userService.getUserById(medicalRecordDTO.getPatientId());
        if (patient instanceof Patient) {
            medicalRecord.setPatient((Patient) patient);
        }

        User specialist = userService.getUserById(medicalRecordDTO.getDoctorId());
        if (specialist instanceof Specialist) {
            medicalRecord.setDoctor((Specialist) specialist);
        }

        Prescription prescription = prescriptionService.getPrescriptionById(medicalRecordDTO.getPrescriptionId());
        medicalRecord.setPrescription(String.valueOf(prescription));

        medicalRecord.setMedicalProcedure(new ArrayList<>(medicalRecordDTO.getMedicalProcedure()));

        List<LabReport> labReports = new ArrayList<>();
        for (UUID labReportId : medicalRecordDTO.getLabReportIds()) {
            LabReport report = labReportService.getLabReportById(labReportId);
            if (report != null) {
                labReports.add(report);
            }
        }
        medicalRecord.setLabReports(labReports);
    }

    @Mappings({
            @Mapping(target = "patientId", source = "patient.id"),
            @Mapping(target = "doctorId", source = "doctor.id"),
            @Mapping(target = "diagnose", source = "diagnose"),
            @Mapping(target = "doctorConclusion", source = "doctorConclusion"),
            @Mapping(target = "prescriptionId", source = "prescription.id"),
            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
            @Mapping(target = "labReportIds", source = "labReports.id"),
    })
    MedicalRecordDTO toDto(MedicalRecord medicalRecord);
}

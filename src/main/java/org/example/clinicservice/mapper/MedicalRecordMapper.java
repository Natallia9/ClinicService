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
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    @Mapping(source = "recordId", target = "recordId")
    @Mapping(source = "patient.patientId", target = "patientId")
    @Mapping(source = "doctor.specialistId", target = "doctorId")
    @Mapping(source = "diagnose", target = "diagnose")
    @Mapping(source = "doctorConclusion", target = "doctorConclusion")
    @Mapping(source = "prescription", target = "prescription")
    @Mapping(source = "medicalProcedure", target = "medicalProcedure")
    @Mapping(source = "labReports", target = "labReportIds")
    MedicalRecordDTO toDto(MedicalRecord medicalRecord);

    // Маппинг из DTO в сущность
    @Mapping(source = "patientId", target = "patient.patientId")
    @Mapping(source = "doctorId", target = "doctor.specialistId")
    @Mapping(source = "diagnose", target = "diagnose")
    @Mapping(source = "doctorConclusion", target = "doctorConclusion")
    @Mapping(source = "prescription", target = "prescription")
    @Mapping(source = "medicalProcedure", target = "medicalProcedure")
    @Mapping(source = "labReportIds", target = "labReports")
    MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO);


//    @Mappings({
//            @Mapping(target = "patient", source = "patientId"),
//            @Mapping(target = "doctor", source = "doctorId"),
//            @Mapping(target = "diagnose", source = "diagnose"),
//            @Mapping(target = "doctorConclusion", source = "doctorConclusion"),
//            @Mapping(target = "prescription", source = "prescriptionId"),
//            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
//            @Mapping(target = "labReports", source = "labReportIds"),
//    })
//    MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO);
//
//    @AfterMapping
//    default void createdMedicalRecord(
//            @MappingTarget MedicalRecord medicalRecord,
//            MedicalRecordDTO medicalRecordDTO,
//            @Context UserService userService,
//            @Context PrescriptionService prescriptionService,
//            @Context LabReportService labReportService) {
//
//        medicalRecord.setRecordId(UUID.randomUUID());
//
//        User patient = userService.getUserById(medicalRecordDTO.getPatientId());
//        if (patient instanceof Patient) {
//            medicalRecord.setPatient((Patient) patient);
//        }
//
//        User specialist = userService.getUserById(medicalRecordDTO.getDoctorId());
//        if (specialist instanceof Specialist) {
//            medicalRecord.setDoctor((Specialist) specialist);
//        }
//
//        Prescription prescription = prescriptionService.getPrescriptionById(medicalRecordDTO.getPrescriptionId());
//        medicalRecord.setPrescription(String.valueOf(prescription));
//
//        medicalRecord.setMedicalProcedure(new ArrayList<>(medicalRecordDTO.getMedicalProcedure()));
//
//        List<LabReport> labReports = new ArrayList<>();
//        for (UUID labReportId : medicalRecordDTO.getLabReportIds()) {
//            LabReport report = labReportService.getLabReportById(labReportId);
//            if (report != null) {
//                labReports.add(report);
//            }
//        }
//        medicalRecord.setLabReports(labReports);
//    }
//
//    @Mappings({
//            @Mapping(target = "patientId", source = "patient.id"),
//            @Mapping(target = "doctorId", source = "doctor.id"),
//            @Mapping(target = "diagnose", source = "diagnose"),
//            @Mapping(target = "doctorConclusion", source = "doctorConclusion"),
//            @Mapping(target = "prescriptionId", source = "prescription.id"),
//            @Mapping(target = "medicalProcedure", source = "medicalProcedure"),
//            @Mapping(target = "labReportIds", source = "labReports.id"),
//    })
//    MedicalRecordDTO toDto(MedicalRecord medicalRecord);
}

package org.example.clinicservice.controller;

import org.example.clinicservice.dto.MedicalRecordDTO;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.mapper.MedicalRecordMapper;
import org.example.clinicservice.service.interfeces.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.http.MediaType;


import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private MedicalRecordMapper medicalRecordMapper;

    private final UUID recordId = UUID.randomUUID();
    private final UUID patientId = UUID.randomUUID();

    @Test
    void testGetMedicalRecordById() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();

        when(medicalRecordService.getMedicalRecordById(recordId)).thenReturn(medicalRecord);
        when(medicalRecordMapper.toDto(medicalRecord)).thenReturn(medicalRecordDTO);

        mockMvc.perform(get("/api/medical-records/" + recordId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetMedicalRecordsByPatientId() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();

        when(medicalRecordService.getMedicalRecordsByPatientId(patientId)).thenReturn(List.of(medicalRecord));
        when(medicalRecordMapper.toDto(medicalRecord)).thenReturn(medicalRecordDTO);

        mockMvc.perform(get("/api/medical-records/patient/" + patientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testSaveMedicalRecord() throws Exception {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        MedicalRecord medicalRecord = new MedicalRecord();

        when(medicalRecordMapper.toEntity(any(MedicalRecordDTO.class))).thenReturn(medicalRecord);
        when(medicalRecordMapper.toDto(any(MedicalRecord.class))).thenReturn(medicalRecordDTO);

        String fixedUuid = "550e8400-e29b-41d4-a716-446655440000";

        mockMvc.perform(post("/api/medical-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recordId\": \"" + fixedUuid + "\", \"details\": \"Test Record\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testDeleteMedicalRecord() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(recordId);

        mockMvc.perform(delete("/api/medical-records/" + recordId))
                .andExpect(status().isNoContent());
    }
}


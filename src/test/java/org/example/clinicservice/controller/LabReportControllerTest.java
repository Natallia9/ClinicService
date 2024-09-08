package org.example.clinicservice.controller;

import org.example.clinicservice.dto.LabReportDTO;
import org.example.clinicservice.dto.MedicalRecordDTO;
import org.example.clinicservice.entity.LabReport;
import org.example.clinicservice.entity.MedicalRecord;
import org.example.clinicservice.mapper.LabReportMapper;
import org.example.clinicservice.mapper.MedicalRecordMapper;
import org.example.clinicservice.service.interfeces.LabReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class LabReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabReportService labReportService;

    @MockBean
    private LabReportMapper labReportMapper;

    @MockBean
    private MedicalRecordMapper medicalRecordMapper;

    private final UUID reportId = UUID.randomUUID();

    @Test
    void testGetLabReportById() throws Exception {
        LabReport labReport = new LabReport();
        LabReportDTO labReportDTO = new LabReportDTO();

        when(labReportService.getLabReportById(reportId)).thenReturn(labReport);
        when(labReportMapper.toDto(labReport)).thenReturn(labReportDTO);

        mockMvc.perform(get("/api/lab-reports/" + reportId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetLabReportsByDate() throws Exception {
        LocalDateTime reportDate = LocalDateTime.now();
        LabReport labReport = new LabReport();
        LabReportDTO labReportDTO = new LabReportDTO();

        when(labReportService.getLabReportsByDate(reportDate)).thenReturn(Map.of(new MedicalRecord(), List.of(labReport)));
        when(medicalRecordMapper.toDto(any())).thenReturn(new MedicalRecordDTO());
        when(labReportMapper.toDto(labReport)).thenReturn(labReportDTO);

        mockMvc.perform(get("/api/lab-reports/date/" + reportDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testSaveLabReport() throws Exception {
        LabReportDTO labReportDTO = new LabReportDTO();
        LabReport labReport = new LabReport();

        when(labReportMapper.toEntity(any(LabReportDTO.class))).thenReturn(labReport);
        when(labReportMapper.toDto(any(LabReport.class))).thenReturn(labReportDTO);

        mockMvc.perform(post("/api/lab-reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reportId\": \"550e8400-e29b-41d4-a716-446655440000\", \"details\": \"Test Report\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testDeleteLabReport() throws Exception {
        doNothing().when(labReportService).deleteLabReport(reportId);

        mockMvc.perform(delete("/api/lab-reports/" + reportId))
                .andExpect(status().isNoContent());
    }
}


package org.example.clinicservice.controller;

import org.example.clinicservice.dto.PatientVisitHistoryDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.PatientVisitHistory;
import org.example.clinicservice.mapper.PatientVisitHistoryMapper;
import org.example.clinicservice.service.interfeces.PatientVisitHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class PatientVisitHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientVisitHistoryService patientVisitHistoryService;

    @MockBean
    private PatientVisitHistoryMapper patientVisitHistoryMapper;

    private final UUID visitId = UUID.randomUUID();
    private final UUID patientId = UUID.randomUUID();

    @Test
    void testGetVisitHistoryById() throws Exception {
        PatientVisitHistory visitHistory = new PatientVisitHistory();
        PatientVisitHistoryDTO visitHistoryDTO = new PatientVisitHistoryDTO();

        when(patientVisitHistoryService.getVisitHistoryById(visitId)).thenReturn(visitHistory);
        when(patientVisitHistoryMapper.toDto(visitHistory)).thenReturn(visitHistoryDTO);

        mockMvc.perform(get("/api/visit-history/" + visitId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetVisitHistoryByPatient() throws Exception {
        Patient patient = new Patient();
        PatientVisitHistory visitHistory = new PatientVisitHistory();

        when(patientVisitHistoryService.getVisitHistoryByPatient(patientId))
                .thenReturn(Map.of(Optional.of(patient), List.of(visitHistory)));

        mockMvc.perform(get("/api/visit-history/patient/" + patientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testSaveVisitHistory() throws Exception {
        mockMvc.perform(post("/api/visit-history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"visitId\": \"" + visitId + "\", \"visitType\": \"Checkup\", \"patientCondition\": \"Good\"}"))
                .andExpect(status().isCreated());
    }


    @Test
    void testDeleteVisitHistory() throws Exception {
        mockMvc.perform(delete("/api/visit-history/" + visitId))
                .andExpect(status().isNoContent());
    }
}

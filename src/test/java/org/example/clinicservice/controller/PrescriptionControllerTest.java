package org.example.clinicservice.controller;

import org.example.clinicservice.dto.PrescriptionDTO;
import org.example.clinicservice.entity.Prescription;
import org.example.clinicservice.mapper.PrescriptionMapper;
import org.example.clinicservice.service.interfeces.PrescriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@WithMockUser(username = "testuser", roles = {"USER", "ADMIN"})
class PrescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private PrescriptionMapper prescriptionMapper;

    private final UUID prescriptionId = UUID.randomUUID();
    private final UUID patientId = UUID.randomUUID();
    private final UUID doctorId = UUID.randomUUID();

    @Test
    void testGetPrescriptionById() throws Exception {
        Prescription prescription = new Prescription();
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

        when(prescriptionService.getPrescriptionById(prescriptionId)).thenReturn(prescription);
        when(prescriptionMapper.toDto(prescription)).thenReturn(prescriptionDTO);

        mockMvc.perform(get("/api/prescriptions/" + prescriptionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetPrescriptionsByPatientId() throws Exception {
        Prescription prescription = new Prescription();
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

        when(prescriptionService.getPrescriptionsByPatientId(patientId)).thenReturn(List.of(prescription));
        when(prescriptionMapper.toDto(prescription)).thenReturn(prescriptionDTO);

        mockMvc.perform(get("/api/prescriptions/patient/" + patientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetPrescriptionsByDoctorId() throws Exception {
        Prescription prescription = new Prescription();
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

        when(prescriptionService.getPrescriptionsByDoctorId(doctorId)).thenReturn(List.of(prescription));
        when(prescriptionMapper.toDto(prescription)).thenReturn(prescriptionDTO);

        mockMvc.perform(get("/api/prescriptions/doctor/" + doctorId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testSavePrescription() throws Exception {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        Prescription prescription = new Prescription();

        when(prescriptionMapper.toEntity(any(PrescriptionDTO.class))).thenReturn(prescription);
        doNothing().when(prescriptionService).savePrescription(prescription);

        mockMvc.perform(post("/api/prescriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"prescriptionId\": \"550e8400-e29b-41d4-a716-446655440000\", \"medicationName\": \"Test Medication\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeletePrescription() throws Exception {
        doNothing().when(prescriptionService).deletePrescription(prescriptionId);

        mockMvc.perform(delete("/api/prescriptions/" + prescriptionId))
                .andExpect(status().isNoContent());
    }
}

package org.example.clinicservice.controller;

import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.mapper.PatientMapper;
import org.example.clinicservice.mapper.SpecialistMapper;
import org.example.clinicservice.service.interfeces.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private PatientMapper patientMapper;

    @MockBean
    private SpecialistMapper specialistMapper;

    private final UUID patientId = UUID.randomUUID();

    @Test
    void testGetAllPatients() throws Exception {
        Patient patient = new Patient();
        PatientDTO patientDTO = new PatientDTO();

        when(patientService.getAllPatients()).thenReturn(List.of(patient));
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testFindByPhoneNumber() throws Exception {
        Patient patient = new Patient();
        PatientDTO patientDTO = new PatientDTO();

        when(patientService.findByPhoneNumber("123456789")).thenReturn(patient);
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);

        mockMvc.perform(get("/api/patients/phone/123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").exists());
    }

    @Test
    void testFindBySpecialists() throws Exception {
        Patient patient = new Patient();
        Specialist specialist = new Specialist();
        PatientDTO patientDTO = new PatientDTO();
        SpecialistDTO specialistDTO = new SpecialistDTO();

        when(patientService.findBySpecialists(patientId)).thenReturn(Map.of(patient, List.of(specialist)));
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);
        when(specialistMapper.toDto(Optional.of(specialist))).thenReturn(specialistDTO);

        mockMvc.perform(get("/api/patients/" + patientId + "/specialists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
}


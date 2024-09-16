package org.example.clinicservice.controller;

import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.Department;
import org.example.clinicservice.mapper.SpecialistMapper;
import org.example.clinicservice.mapper.PatientMapper;
import org.example.clinicservice.service.interfeces.SpecialistService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SpecialistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialistService specialistService;

    @MockBean
    private SpecialistMapper specialistMapper;

    @MockBean
    private PatientMapper patientMapper;

    private final UUID specialistId = UUID.randomUUID();

    @Test
    void testGetAllSpecialists() throws Exception {
        Specialist specialist = new Specialist();
        SpecialistDTO specialistDTO = new SpecialistDTO();

        when(specialistService.getAllSpecialists()).thenReturn(List.of(specialist));
        when(specialistMapper.toDto(specialist)).thenReturn(specialistDTO);  // Убрали Optional

        mockMvc.perform(get("/api/specialists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testFindSpecialistsByAreaOfSpecialization() throws Exception {
        Specialist specialist = new Specialist();
        SpecialistDTO specialistDTO = new SpecialistDTO();

        when(specialistService.findSpecialistsByAreaOfSpecialization("Cardiology")).thenReturn(List.of(specialist));
        when(specialistMapper.toDto(specialist)).thenReturn(specialistDTO);  // Убрали Optional

        mockMvc.perform(get("/api/specialists/specialization")
                        .param("areaOfSpecialization", "Cardiology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testFindSpecialistsByAvailability() throws Exception {
        Specialist specialist = new Specialist();
        SpecialistDTO specialistDTO = new SpecialistDTO();

        when(specialistService.findSpecialistsByAvailability(true)).thenReturn(List.of(specialist));
        when(specialistMapper.toDto(specialist)).thenReturn(specialistDTO);  // Убрали Optional

        mockMvc.perform(get("/api/specialists/availability")
                        .param("availability", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testFindSpecialistsByDepartment() throws Exception {
        Specialist specialist = new Specialist();
        SpecialistDTO specialistDTO = new SpecialistDTO();

        when(specialistService.findSpecialistsByDepartment(Department.CARDIOLOGY)).thenReturn(List.of(specialist));
        when(specialistMapper.toDto(specialist)).thenReturn(specialistDTO);  // Убрали Optional

        mockMvc.perform(get("/api/specialists/department")
                        .param("department", "CARDIOLOGY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testFindSpecialistsByPatient() throws Exception {
        Specialist specialist = new Specialist();
        Patient patient = new Patient();
        SpecialistDTO specialistDTO = new SpecialistDTO();
        PatientDTO patientDTO = new PatientDTO();

        when(specialistService.findSpecialistsByPatient(specialistId)).thenReturn(Map.of(specialist, List.of(patient)));
        when(specialistMapper.toDto(specialist)).thenReturn(specialistDTO);  // Убрали Optional
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);

        mockMvc.perform(get("/api/specialists/patient/" + specialistId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
}

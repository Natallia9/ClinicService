package org.example.clinicservice.controller;

import org.example.clinicservice.entity.SystemOwner;
import org.example.clinicservice.service.interfeces.SystemOwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SystemOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SystemOwnerService systemOwnerService;

    private final UUID ownerId = UUID.randomUUID();

    @Test
    void testGetSystemOwnerById() throws Exception {
        SystemOwner systemOwner = new SystemOwner();
        when(systemOwnerService.getSystemOwnerById(ownerId)).thenReturn(systemOwner);

        mockMvc.perform(get("/api/system-owners/" + ownerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetSystemOwnerByEmail() throws Exception {
        SystemOwner systemOwner = new SystemOwner();
        when(systemOwnerService.getSystemOwnerByEmail("test@example.com")).thenReturn(systemOwner);

        mockMvc.perform(get("/api/system-owners/email")
                        .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetSystemOwnerByPhoneNumber() throws Exception {
        SystemOwner systemOwner = new SystemOwner();
        when(systemOwnerService.findByPhoneNumber("1234567890")).thenReturn(systemOwner);

        mockMvc.perform(get("/api/system-owners/phone")
                        .param("phoneNumber", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testSaveSystemOwner() throws Exception {
        SystemOwner systemOwner = new SystemOwner();
        when(systemOwnerService.saveSystemOwner(any(SystemOwner.class))).thenReturn(systemOwner);

        mockMvc.perform(post("/api/system-owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ownerId\": \"550e8400-e29b-41d4-a716-446655440000\", \"name\": \"Test Owner\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteSystemOwner() throws Exception {
        doNothing().when(systemOwnerService).deleteSystemOwner(ownerId);

        mockMvc.perform(delete("/api/system-owners/" + ownerId))
                .andExpect(status().isNoContent());
    }
}


package org.example.clinicservice.controller;

import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.service.interfeces.RolesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class RolesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RolesService rolesService;

    private final UUID roleId = UUID.randomUUID();

    @Test
    void getRoleByIdPositiveTest() throws Exception {
        Roles role = new Roles();
        when(rolesService.getRoleById(roleId)).thenReturn(role);

        mockMvc.perform(get("/api/roles/" + roleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getRoleByIdNegativeTest() throws Exception {
        UUID nonExistentID = UUID.randomUUID();
        String expectedErrorMessage = "{\"message\":\"ROLE_ID_NOT_FOUND_EXCEPTION\",\"errorCode\":\"404 NOT_FOUND\"}";

        mockMvc.perform(get("/api/roles/" + nonExistentID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("ROLE_ID_NOT_FOUND_EXCEPTION"));
    }

    @Test
    void getRolesByUserIdTest() throws Exception {
        UUID userId = UUID.randomUUID();
        Set<Roles> roles = Set.of(new Roles());

        when(rolesService.getRolesByUserId(userId)).thenReturn(roles);

        mockMvc.perform(get("/api/roles/user/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void saveRoleTest() throws Exception {
        Roles role = new Roles();
        doNothing().when(rolesService).saveRole(role);

        mockMvc.perform(post("/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roleName\": \"ROLE_ADMIN\"}"))  // Замените на правильные данные JSON
                .andExpect(status().isCreated());
    }

    @Test
    void deleteRoleTest() throws Exception {
        doNothing().when(rolesService).deleteRole(roleId);

        mockMvc.perform(delete("/api/roles/" + roleId))
                .andExpect(status().isNoContent());
    }
}

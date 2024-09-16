package org.example.clinicservice.controller;

import org.example.clinicservice.dto.JwtAuthenticationResponse;
import org.example.clinicservice.dto.SignInRequest;
import org.example.clinicservice.dto.SignUpRequest;
import org.example.clinicservice.security.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void testSignUp() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testuser");
        signUpRequest.setPassword("password123");

        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
        jwtResponse.setToken("sampleToken");

        when(authenticationService.signUp(signUpRequest)).thenReturn(jwtResponse);

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("sampleToken"));
    }

    @Test
    void testSignIn() throws Exception {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("testuser");
        signInRequest.setPassword("password123");

        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
        jwtResponse.setToken("sampleToken");

        when(authenticationService.signIn(signInRequest)).thenReturn(jwtResponse);

        mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("sampleToken"));
    }
}


package org.example.clinicservice.controller;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.ServiceName;
import org.example.clinicservice.entity.finance.Payment;
import org.example.clinicservice.service.interfeces.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private final UUID paymentId = UUID.randomUUID();
    private final UUID patientId = UUID.randomUUID();

    @Test
    void testGetPaymentById() throws Exception {
        Payment payment = new Payment();
        when(paymentService.getPaymentById(paymentId)).thenReturn(payment);

        mockMvc.perform(get("/api/payments/" + paymentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetPaymentsByServiceName() throws Exception {
        Payment payment = new Payment();
        when(paymentService.getPaymentsByServiceName(ServiceName.CONSULTATION)).thenReturn(List.of(payment));

        mockMvc.perform(get("/api/payments/service")
                        .param("serviceName", "CONSULTATION"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetPaymentsByPatient() throws Exception {
        Payment payment = new Payment();
        Patient patient = new Patient();
        when(paymentService.getPaymentsByPatient(patientId)).thenReturn(Map.of(patient, List.of(payment)));

        mockMvc.perform(get("/api/payments/patient/" + patientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testSavePayment() throws Exception {
        doNothing().when(paymentService).savePayment(any(Payment.class));

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"paymentId\": \"550e8400-e29b-41d4-a716-446655440000\", \"amount\": 100.00}"))
                .andExpect(status().isCreated());
    }


    @Test
    void testDeletePayment() throws Exception {
        doNothing().when(paymentService).deletePayment(paymentId);

        mockMvc.perform(delete("/api/payments/" + paymentId))
                .andExpect(status().isNoContent());
    }
}


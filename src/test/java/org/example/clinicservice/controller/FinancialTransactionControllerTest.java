package org.example.clinicservice.controller;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.finance.FinancialTransaction;
import org.example.clinicservice.service.interfeces.FinancialTransactionService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FinancialTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinancialTransactionService financialTransactionService;

    private final UUID patientId = UUID.randomUUID();
    private final UUID transactionId = UUID.randomUUID();

    @Test
    void testGetTransactionsByPatientId() throws Exception {
        FinancialTransaction transaction = new FinancialTransaction();
        when(financialTransactionService.getTransactionsByPatientId(patientId)).thenReturn(List.of(transaction));

        mockMvc.perform(get("/api/financial-transactions/patient/" + patientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetTransactionsByDate() throws Exception {
        LocalDateTime transactionDate = LocalDateTime.now();
        FinancialTransaction transaction = new FinancialTransaction();
        when(financialTransactionService.getTransactionsByDate(transactionDate)).thenReturn(Map.of(new Patient(), List.of(transaction)));

        mockMvc.perform(get("/api/financial-transactions/date/" + transactionDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testSaveTransaction() throws Exception {
        FinancialTransaction transaction = new FinancialTransaction();
        doNothing().when(financialTransactionService).saveTransaction(any(FinancialTransaction.class));

        mockMvc.perform(post("/api/financial-transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"transactionId\": \"550e8400-e29b-41d4-a716-446655440000\", \"amount\": 100.00}"))
                .andExpect(status().isCreated());
    }


    @Test
    void testDeleteTransaction() throws Exception {
        doNothing().when(financialTransactionService).deleteTransaction(transactionId);

        mockMvc.perform(delete("/api/financial-transactions/" + transactionId))
                .andExpect(status().isNoContent());
    }
}


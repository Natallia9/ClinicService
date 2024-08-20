package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.finance.FinancialTransaction;
import org.example.clinicservice.service.interfeces.FinancialTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/financial-transactions")
@RequiredArgsConstructor
public class FinancialTransactionController {

    private final FinancialTransactionService financialTransactionService;

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FinancialTransaction> getTransactionsByPatientId(@PathVariable UUID patientId) {
        return financialTransactionService.getTransactionsByPatientId(patientId);
    }

    @GetMapping("/date/{transactionDate}")
    @ResponseStatus(HttpStatus.OK)
    public Map<Patient, List<FinancialTransaction>> getTransactionsByDate(@PathVariable LocalDateTime transactionDate) {
        return financialTransactionService.getTransactionsByDate(transactionDate);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FinancialTransaction saveTransaction(@RequestBody FinancialTransaction financialTransaction) {
        financialTransactionService.saveTransaction(financialTransaction);
        return financialTransaction;
    }

    @DeleteMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable UUID transactionId) {
        financialTransactionService.deleteTransaction(transactionId);
    }
}





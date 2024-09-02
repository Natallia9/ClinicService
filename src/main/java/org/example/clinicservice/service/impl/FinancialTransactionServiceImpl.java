package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.finance.FinancialTransaction;
import org.example.clinicservice.exceptions.financialTransactionExceptions.TransactionsNotFoundException;
import org.example.clinicservice.repository.FinancialTransactionRepository;
import org.example.clinicservice.service.interfeces.FinancialTransactionService;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialTransactionServiceImpl implements FinancialTransactionService {

    private final FinancialTransactionRepository financialTransactionRepository;

    @Override
    public List<FinancialTransaction> getTransactionsByPatientId(UUID patientId) {
        List<FinancialTransaction> transactions = financialTransactionRepository.findByPatientId(patientId);
        if (transactions.isEmpty()) {
            throw new TransactionsNotFoundException(ErrorMessage.TRANSACTIONS_NOT_FOUND);
        }
        return transactions;
    }

    @Override
    public Map<Patient, List<FinancialTransaction>> getTransactionsByDate(LocalDateTime transactionDate) {
        List<FinancialTransaction> transactions = financialTransactionRepository.findByTransactionDate(transactionDate);
        if (transactions.isEmpty()) {
            throw new TransactionsNotFoundException(ErrorMessage.TRANSACTIONS_NOT_FOUND);
        }
        return transactions.stream()
                .collect(Collectors.groupingBy(FinancialTransaction::getPatient));
    }

    @Override
    public void saveTransaction(FinancialTransaction financialTransaction) {
        financialTransactionRepository.save(financialTransaction);
    }

    @Override
    public void deleteTransaction(UUID transactionId) {

        financialTransactionRepository.deleteById(transactionId);
    }
}

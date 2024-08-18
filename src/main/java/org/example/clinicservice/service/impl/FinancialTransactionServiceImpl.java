package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.finance.FinancialTransaction;
import org.example.clinicservice.exceptions.financialTransactionExceptions.TransactionAlreadyExistsException;
import org.example.clinicservice.exceptions.financialTransactionExceptions.TransactionNotFoundException;
import org.example.clinicservice.exceptions.financialTransactionExceptions.TransactionSaveException;
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

        try {
            List<FinancialTransaction> transactions = financialTransactionRepository.findByPatientId(patientId);
            if (transactions.isEmpty()) {
                throw new TransactionsNotFoundException(ErrorMessage.TRANSACTIONS_NOT_FOUND);
            }
            return transactions;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving transactions for patient ID: " + patientId, e);
        }
    }

    @Override
    public Map<Patient, List<FinancialTransaction>> getTransactionsByDate(LocalDateTime transactionDate) {

        try {
            List<FinancialTransaction> transactions = financialTransactionRepository.findByTransactionDate(transactionDate);

            if (transactions.isEmpty()) {
                throw new TransactionsNotFoundException(ErrorMessage.TRANSACTIONS_NOT_FOUND);
            }

            Map<Patient, List<FinancialTransaction>> patientTransactions = transactions.stream()
                    .collect(Collectors.groupingBy(FinancialTransaction::getPatient));

            return patientTransactions;

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve transactions for date: " + transactionDate, e);
        }
    }

    @Override
    public void saveTransaction(FinancialTransaction financialTransaction) {

        if (financialTransactionRepository.existsById(financialTransaction.getTransactionId())) {
            throw new TransactionAlreadyExistsException(ErrorMessage.TRANSACTION_ALREADY_EXISTS);
        }

        try {
            financialTransactionRepository.save(financialTransaction);
        } catch (Exception e) {
            throw new TransactionSaveException("Failed to save financial transaction due to an unexpected error: " + e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(UUID transactionId) {

        if (!financialTransactionRepository.existsById(transactionId)) {
            throw new TransactionNotFoundException(ErrorMessage.TRANSACTION_NOT_FOUND);
        }

        try {
            financialTransactionRepository.deleteById(transactionId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete financial transaction with ID: " + transactionId, e);
        }
    }
}

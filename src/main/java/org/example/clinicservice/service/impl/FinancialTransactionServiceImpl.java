package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.enums.PaymentMethod;
import org.example.clinicservice.entity.enums.TransactionType;
import org.example.clinicservice.entity.finance.FinancialTransaction;
import org.example.clinicservice.repository.FinancialTransactionRepository;
import org.example.clinicservice.service.interfeces.FinancialTransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinancialTransactionServiceImpl implements FinancialTransactionService {

    private final FinancialTransactionRepository financialTransactionRepository;

    @Override
    public List<FinancialTransaction> getTransactionsByPatientId(UUID patientId) {

        return financialTransactionRepository.findByPatientId(patientId);
    }

    @Override
    public List<FinancialTransaction> getTransactionsByDate(LocalDateTime transactionDate) {

        return financialTransactionRepository.findByTransactionDate(transactionDate);
    }

    @Override
    public List<FinancialTransaction> getTransactionsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return financialTransactionRepository.findByTransactionDateBetween(startDate, endDate);
    }

    @Override
    public List<FinancialTransaction> getTransactionsByPaymentMethod(PaymentMethod paymentMethod) {
        return financialTransactionRepository.findByPaymentMethod(paymentMethod);
    }

    @Override
    public List<FinancialTransaction> getTransactionsByType(TransactionType transactionType) {

        return financialTransactionRepository.findByTransactionType(transactionType);
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

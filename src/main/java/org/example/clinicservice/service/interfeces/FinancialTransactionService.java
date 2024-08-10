package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.enums.PaymentMethod;
import org.example.clinicservice.entity.enums.TransactionType;
import org.example.clinicservice.entity.finance.FinancialTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FinancialTransactionService {

    List<FinancialTransaction> getTransactionsByPatientId(UUID patientId);
    List<FinancialTransaction> getTransactionsByDate(LocalDateTime transactionDate);
    List<FinancialTransaction> getTransactionsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    List<FinancialTransaction> getTransactionsByPaymentMethod(PaymentMethod paymentMethod);
    List<FinancialTransaction> getTransactionsByType(TransactionType transactionType);
    void saveTransaction(FinancialTransaction financialTransaction);
    void deleteTransaction(UUID transactionId);
}

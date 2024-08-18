package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.PaymentMethod;
import org.example.clinicservice.entity.enums.TransactionType;
import org.example.clinicservice.entity.finance.FinancialTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FinancialTransactionService {

    List<FinancialTransaction> getTransactionsByPatientId(UUID patientId);
    Map<Patient, List<FinancialTransaction>> getTransactionsByDate(LocalDateTime transactionDate);
    void saveTransaction(FinancialTransaction financialTransaction);
    void deleteTransaction(UUID transactionId);
}

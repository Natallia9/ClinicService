package org.example.clinicservice.repository;

import org.example.clinicservice.entity.finance.FinancialTransaction;
import org.example.clinicservice.entity.enums.PaymentMethod;
import org.example.clinicservice.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, UUID> {

    List<FinancialTransaction> findByPatientId(UUID patientId);
    List<FinancialTransaction> findByTransactionDate(LocalDateTime transactionDate);
    List<FinancialTransaction> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<FinancialTransaction> findByPaymentMethod(PaymentMethod paymentMethod);
    List<FinancialTransaction> findByTransactionType(TransactionType transactionType);
    List<FinancialTransaction> findByDescriptionContaining(String description);
}

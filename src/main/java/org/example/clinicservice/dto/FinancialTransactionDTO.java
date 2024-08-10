package org.example.clinicservice.dto;

import lombok.Data;
import org.example.clinicservice.entity.enums.PaymentMethod;
import org.example.clinicservice.entity.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class FinancialTransactionDTO {

    private UUID transactionId;
    private LocalDateTime transactionDate;
    private double amount;
    private String description;
    private PaymentMethod paymentMethod;
    private TransactionType transactionType;
}

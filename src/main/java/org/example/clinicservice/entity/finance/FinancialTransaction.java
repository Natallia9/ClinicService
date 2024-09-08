package org.example.clinicservice.entity.finance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.PaymentMethod;
import org.example.clinicservice.entity.enums.TransactionType;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "financial_transactions")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a financial transaction made by a patient.")
public class FinancialTransaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "transaction_id")
    @Schema(description = "Unique identifier of the transaction", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID transactionId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @Schema(description = "The patient associated with the transaction")
    private Patient patient;

    @Column(name = "transaction_date")
    @Schema(description = "Date and time of the transaction", example = "2023-12-01T12:30:00")
    private LocalDateTime transactionDate;

    @Column(name = "amount")
    @Schema(description = "The amount of the transaction", example = "150.50")
    private double amount;

    @Column(name = "description")
    @Schema(description = "Description of the transaction", example = "Payment for blood test")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    @Schema(description = "The payment method used", example = "CARD")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    @Schema(description = "The type of transaction", example = "SERVICE_PAYMENT")
    private TransactionType transactionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialTransaction that = (FinancialTransaction) o;
        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }
}

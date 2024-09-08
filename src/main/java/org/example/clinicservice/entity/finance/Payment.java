package org.example.clinicservice.entity.finance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.ServiceName;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "payment_services")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a payment service provided to a patient.")
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "service_id")
    @Schema(description = "Unique identifier of the payment service", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID serviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_name")
    @Schema(description = "Name of the service provided", example = "CONSULTATION")
    private ServiceName serviceName;

    @Column(name = "price")
    @Schema(description = "Price of the service", example = "100.00")
    private double price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @Schema(description = "The patient who received the service")
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment that = (Payment) o;
        return Objects.equals(serviceId, that.serviceId) && serviceName == that.serviceName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName);
    }
}

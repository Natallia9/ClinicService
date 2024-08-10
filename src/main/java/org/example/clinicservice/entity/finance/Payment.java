package org.example.clinicservice.entity.finance;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "service_id")
    private UUID serviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_name")
    private ServiceName serviceName;

    @Column(name = "price")
    private double price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
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

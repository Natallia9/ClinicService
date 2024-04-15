package org.example.clinicservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.enums.ServiceName;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "paid_services")
@Getter
@Setter
@NoArgsConstructor
public class PaidService extends Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    private UUID serviceId;

    @Enumerated(EnumType.STRING)
    private ServiceName serviceName;

    @Column(name = "price")
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidService that = (PaidService) o;
        return Objects.equals(serviceId, that.serviceId) && serviceName == that.serviceName;
    }
    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName);
    }
}

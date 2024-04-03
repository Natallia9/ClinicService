package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.ServiceName;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "additional_service")
@Getter
@Setter
@NoArgsConstructor
public class AdditionalService {

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
        AdditionalService that = (AdditionalService) o;
        return Objects.equals(serviceId, that.serviceId) && serviceName == that.serviceName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName);
    }
}

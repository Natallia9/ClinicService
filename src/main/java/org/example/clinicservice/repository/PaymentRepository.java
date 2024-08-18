package org.example.clinicservice.repository;

import org.example.clinicservice.entity.finance.Payment;
import org.example.clinicservice.entity.enums.ServiceName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Payment findByServiceId(UUID serviceId);
    List<Payment> findByServiceName(ServiceName serviceName);
    List<Payment> findByPatientId(UUID patientId);
}

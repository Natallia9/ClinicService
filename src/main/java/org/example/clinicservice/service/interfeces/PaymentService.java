package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.ServiceName;
import org.example.clinicservice.entity.finance.Payment;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PaymentService {

    Payment getPaymentById(UUID paymentId);
    List<Payment> getPaymentsByServiceName(ServiceName serviceName);
    Map<Patient, List<Payment>> getPaymentsByPatient(UUID patientId);
    void savePayment(Payment payment);
    void deletePayment(UUID paymentId);
}

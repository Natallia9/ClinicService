package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.enums.ServiceName;
import org.example.clinicservice.entity.finance.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    Payment getPaymentByServiceId(UUID serviceId);
    List<Payment> getPaymentsByServiceName(ServiceName serviceName);
    List<Payment> getPaymentsByPrice(double price);
    List<Payment> getPaymentsByPatientId(UUID patientId);
    void savePayment(Payment payment);
    void deletePayment(UUID paymentId);
}

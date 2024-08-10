package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.enums.ServiceName;
import org.example.clinicservice.entity.finance.Payment;
import org.example.clinicservice.repository.PaymentRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentByServiceId(UUID serviceId) {

        return paymentRepository.findByServiceId(serviceId);
    }

    @Override
    public List<Payment> getPaymentsByServiceName(ServiceName serviceName) {

        return paymentRepository.findByServiceName(serviceName);
    }

    @Override
    public List<Payment> getPaymentsByPrice(double price) {

        return paymentRepository.findByPrice(price);
    }

    @Override
    public List<Payment> getPaymentsByPatientId(UUID patientId) {

        return paymentRepository.findByPatientId(patientId);
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(UUID paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}

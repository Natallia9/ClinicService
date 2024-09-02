package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.ServiceName;
import org.example.clinicservice.entity.finance.Payment;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentNotFoundException;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentsNotFoundException;
import org.example.clinicservice.repository.PaymentRepository;
import org.example.clinicservice.service.interfeces.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentById(UUID paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(ErrorMessage.PAYMENT_NOT_FOUND));
    }

    @Override
    public List<Payment> getPaymentsByServiceName(ServiceName serviceName) {
        List<Payment> payments = paymentRepository.findByServiceName(serviceName);
        if (payments.isEmpty()) {
            throw new PaymentsNotFoundException(ErrorMessage.PAYMENTS_NOT_FOUND_FOR_SERVICE + serviceName);
        }
        return payments;
    }

    @Override
    public Map<Patient, List<Payment>> getPaymentsByPatient(UUID patientId) {
        List<Payment> payments = paymentRepository.findByPatientId(patientId);
        if (payments.isEmpty()) {
            throw new PaymentsNotFoundException(ErrorMessage.PAYMENTS_NOT_FOUND_FOR_PATIENT + patientId);
        }
        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getPatient));
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(UUID paymentId) {
        getPaymentById(paymentId);
        paymentRepository.deleteById(paymentId);
    }
}

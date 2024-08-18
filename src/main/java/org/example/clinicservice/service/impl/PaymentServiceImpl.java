package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.entity.enums.ServiceName;
import org.example.clinicservice.entity.finance.Payment;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentAlreadyExistsException;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentNotFoundException;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentSaveException;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentsNotFoundException;
import org.example.clinicservice.repository.PaymentRepository;
import org.example.clinicservice.repository.SpecialistRepository;
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

        try {
            Payment payment = paymentRepository.findByServiceId(paymentId);
            if(paymentId == null){
                throw new PaymentNotFoundException(ErrorMessage.PAYMENT_NOT_FOUND);
            }
            return payment;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving payment with id " + paymentId, e);
        }
    }

    @Override
    public List<Payment> getPaymentsByServiceName(ServiceName serviceName) {

        try {
            List<Payment> payments = paymentRepository.findByServiceName(serviceName);

            if (payments.isEmpty()) {
                throw new PaymentsNotFoundException(ErrorMessage.PAYMENTS_NOT_FOUND_FOR_SERVICE + serviceName);
            }

            return payments;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving payments: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<Patient, List<Payment>> getPaymentsByPatient(UUID patientId) {

        try {
            List<Payment> payments = paymentRepository.findByPatientId(patientId);

            if (payments.isEmpty()) {
                throw new PaymentsNotFoundException(ErrorMessage.PAYMENTS_NOT_FOUND_FOR_PATIENT + patientId);
            }
            Map<Patient, List<Payment>> patientPayments = payments.stream()
                    .collect(Collectors.groupingBy(Payment::getPatient));

            return patientPayments;
        }catch (IllegalArgumentException e) {
            throw new RuntimeException("An unexpected error occurred while retrieving payments: " + e.getMessage(), e);
        }
    }

    @Override
    public void savePayment(Payment payment) {

        if (paymentRepository.existsById(payment.getServiceId())) {
            throw new PaymentAlreadyExistsException(ErrorMessage.PAYMENT_ALREADY_EXISTS);
        }
        try {
            paymentRepository.save(payment);
        } catch (Exception e) {
            throw new PaymentSaveException("Failed to save payment due to an unexpected error: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletePayment(UUID paymentId) {

        if (!paymentRepository.existsById(paymentId)) {
            throw new PaymentNotFoundException(ErrorMessage.PAYMENT_NOT_FOUND);
        }

        try {
            paymentRepository.deleteById(paymentId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete payment with ID: " + paymentId, e);
        }
    }
}

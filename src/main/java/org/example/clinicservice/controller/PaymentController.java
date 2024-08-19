package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.enums.ServiceName;
import org.example.clinicservice.entity.finance.Payment;
import org.example.clinicservice.service.interfeces.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPaymentById(@PathVariable UUID paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/service")
    @ResponseStatus(HttpStatus.OK)
    public List<Payment> getPaymentsByServiceName(@RequestParam ServiceName serviceName) {
        return paymentService.getPaymentsByServiceName(serviceName);
    }

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<Patient, List<Payment>> getPaymentsByPatient(@PathVariable UUID patientId) {
        return paymentService.getPaymentsByPatient(patientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePayment(@RequestBody Payment payment) {
        paymentService.savePayment(payment);
    }

    @DeleteMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable UUID paymentId) {
        paymentService.deletePayment(paymentId);
    }
}


package org.example.clinicservice.exceptions.paymentExceptions;

public class PaymentsNotFoundException extends RuntimeException {
    public PaymentsNotFoundException(String message) {
        super(message);
    }
}

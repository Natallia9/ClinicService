package org.example.clinicservice.exceptions.paymentExceptions;

public class PaymentSaveException extends RuntimeException {
    public PaymentSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}

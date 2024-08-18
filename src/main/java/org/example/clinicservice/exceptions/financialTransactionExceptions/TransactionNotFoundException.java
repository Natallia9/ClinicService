package org.example.clinicservice.exceptions.financialTransactionExceptions;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}

package org.example.clinicservice.exceptions.financialTransactionExceptions;

public class TransactionsNotFoundException extends RuntimeException {
    public TransactionsNotFoundException(String message) {
        super(message);
    }
}

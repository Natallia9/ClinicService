package org.example.clinicservice.exceptions.financialTransactionExceptions;

public class TransactionAlreadyExistsException extends RuntimeException {
    public TransactionAlreadyExistsException(String message) {
        super(message);
    }
}

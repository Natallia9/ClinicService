package org.example.clinicservice.exceptions.financialTransactionExceptions;

public class TransactionSaveException extends RuntimeException {
    public TransactionSaveException(String message) {
        super(message);
    }
}

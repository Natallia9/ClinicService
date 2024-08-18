package org.example.clinicservice.exceptions.visitHistoryExceptions;

public class VisitHistoryAlreadyExistsException extends RuntimeException {
    public VisitHistoryAlreadyExistsException(String message) {
        super(message);
    }
}

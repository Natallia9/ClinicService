package org.example.clinicservice.exceptions.visitHistoryExceptions;

public class VisitHistoryNotFoundException extends RuntimeException {
    public VisitHistoryNotFoundException(String message) {
        super(message);
    }
}

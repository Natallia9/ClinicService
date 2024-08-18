package org.example.clinicservice.exceptions.patientExceptions;

public class InvalidPatientException extends RuntimeException {
    public InvalidPatientException(String message) {
        super(message);
    }
}


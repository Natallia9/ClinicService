package org.example.clinicservice.exceptions.patientExeptions;

public class InvalidPatientException extends RuntimeException {
    public InvalidPatientException(String message) {
        super(message);
    }
}


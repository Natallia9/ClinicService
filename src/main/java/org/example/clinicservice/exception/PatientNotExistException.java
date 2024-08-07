package org.example.clinicservice.exception;

public class PatientNotExistException extends RuntimeException {

    public PatientNotExistException(String message) {
        super(message);
    }
}

package org.example.clinicservice.exceptions;

public class PatientNotExistException extends RuntimeException {

    public PatientNotExistException(String message) {
        super(message);
    }
}

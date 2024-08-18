package org.example.clinicservice.exceptions.specialistExceptions;

public class SpecializationNotFoundException extends RuntimeException {
    public SpecializationNotFoundException(String message) {
        super(message);
    }
}

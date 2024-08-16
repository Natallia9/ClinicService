package org.example.clinicservice.exceptions.specialistExeptions;

public class SpecializationNotFoundException extends RuntimeException {
    public SpecializationNotFoundException(String message) {
        super(message);
    }
}

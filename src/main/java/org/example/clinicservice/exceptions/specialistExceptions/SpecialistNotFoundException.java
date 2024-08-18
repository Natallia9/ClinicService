package org.example.clinicservice.exceptions.specialistExceptions;

public class SpecialistNotFoundException extends RuntimeException {

    public SpecialistNotFoundException(String message) {
        super(message);
    }
}

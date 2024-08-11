package org.example.clinicservice.exceptions.specialistExeptions;

public class SpecialistNotFoundException extends RuntimeException{
    public SpecialistNotFoundException(String message) {
        super(message);
    }
}

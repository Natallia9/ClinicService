package org.example.clinicservice.exceptions.specialistExceptions;

public class SpecialistsNotFoundException extends RuntimeException{
    public SpecialistsNotFoundException(String message) {
        super(message);
    }
}

package org.example.clinicservice.exceptions.specialistExeptions;

public class SpecialistsNotFoundException extends RuntimeException{
    public SpecialistsNotFoundException(String message) {
        super(message);
    }
}

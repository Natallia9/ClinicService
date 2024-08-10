package org.example.clinicservice.exceptions;

public class SpecialistNotExistException extends RuntimeException{

    public SpecialistNotExistException(String message) {
        super(message);
    }
}

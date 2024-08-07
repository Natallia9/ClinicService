package org.example.clinicservice.exception;

public class SpecialistNotExistException extends RuntimeException{

    public SpecialistNotExistException(String message) {
        super(message);
    }
}

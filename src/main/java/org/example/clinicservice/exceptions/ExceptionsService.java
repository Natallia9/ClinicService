package org.example.clinicservice.exceptions;

public class ExceptionsService {

    public static class SpecialistNotFoundException extends RuntimeException {
        public SpecialistNotFoundException(String message) {
            super(message);
        }
    }
}

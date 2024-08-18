package org.example.clinicservice.exceptions.prescriptionExceptions;

public class PrescriptionsNotFoundException extends RuntimeException {
    public PrescriptionsNotFoundException(String message) {
        super(message);
    }
}

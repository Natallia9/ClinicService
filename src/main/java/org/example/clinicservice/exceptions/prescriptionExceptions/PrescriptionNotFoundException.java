package org.example.clinicservice.exceptions.prescriptionExceptions;


public class PrescriptionNotFoundException extends RuntimeException {
    public PrescriptionNotFoundException(String message) {
        super(message);
    }
}

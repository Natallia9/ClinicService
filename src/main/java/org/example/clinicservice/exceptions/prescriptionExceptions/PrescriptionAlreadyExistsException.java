package org.example.clinicservice.exceptions.prescriptionExceptions;

public class PrescriptionAlreadyExistsException extends RuntimeException {
    public PrescriptionAlreadyExistsException(String message) {
        super(message);
    }
}
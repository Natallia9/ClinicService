package org.example.clinicservice.exceptions.medicalRecordExceptions;

public class MedicalRecordNotFoundException extends RuntimeException {
    public MedicalRecordNotFoundException(String message) {
        super(message);
    }
}
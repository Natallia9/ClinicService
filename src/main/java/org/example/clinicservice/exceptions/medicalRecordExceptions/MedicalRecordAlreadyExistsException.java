package org.example.clinicservice.exceptions.medicalRecordExceptions;

public class MedicalRecordAlreadyExistsException extends RuntimeException {
    public MedicalRecordAlreadyExistsException(String message) {
        super(message);
    }
}

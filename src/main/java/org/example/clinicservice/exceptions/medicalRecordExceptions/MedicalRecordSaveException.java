package org.example.clinicservice.exceptions.medicalRecordExceptions;

public class MedicalRecordSaveException extends RuntimeException {
    public MedicalRecordSaveException(String message) {
        super(message);
    }
}

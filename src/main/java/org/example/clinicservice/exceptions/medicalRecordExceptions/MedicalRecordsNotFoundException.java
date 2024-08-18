package org.example.clinicservice.exceptions.medicalRecordExceptions;

public class MedicalRecordsNotFoundException extends RuntimeException {
    public MedicalRecordsNotFoundException(String message) {
        super(message);
    }
}

package org.example.clinicservice.exceptions.patientExceptions;

public class PatientRetrievalException extends RuntimeException {
    public PatientRetrievalException(String message) {
        super(message);
    }
}

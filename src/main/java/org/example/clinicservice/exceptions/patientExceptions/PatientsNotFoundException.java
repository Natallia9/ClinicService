package org.example.clinicservice.exceptions.patientExceptions;

public class PatientsNotFoundException extends RuntimeException {
    public PatientsNotFoundException(String message) {
        super(message);
    }
}

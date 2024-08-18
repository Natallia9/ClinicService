package org.example.clinicservice.exceptions.labReportExceptions;

public class LabReportNotFoundException extends RuntimeException {
    public LabReportNotFoundException(String message) {
        super(message);
    }
}
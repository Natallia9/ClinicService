package org.example.clinicservice.exceptions.labReportExceptions;

public class LabReportsNotFoundException extends RuntimeException {
    public LabReportsNotFoundException(String message) {
        super(message);
    }
}
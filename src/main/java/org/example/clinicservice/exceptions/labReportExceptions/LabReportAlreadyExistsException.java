package org.example.clinicservice.exceptions.labReportExceptions;

public class LabReportAlreadyExistsException extends RuntimeException {
    public LabReportAlreadyExistsException(String message) {
        super(message);
    }
}

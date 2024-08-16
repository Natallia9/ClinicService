package org.example.clinicservice.exceptions.systemOwnerExceptions;

public class SystemOwnerExistsException extends RuntimeException {
    public SystemOwnerExistsException(String message) {
        super(message);
    }
}


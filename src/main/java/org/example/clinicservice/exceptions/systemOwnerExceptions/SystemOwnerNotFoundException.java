package org.example.clinicservice.exceptions.systemOwnerExceptions;

public class SystemOwnerNotFoundException extends RuntimeException {
    public SystemOwnerNotFoundException(String message) {
        super(message);
    }
}

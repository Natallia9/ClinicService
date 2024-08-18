package org.example.clinicservice.exceptions.roleExceptions;

public class RolesNotFoundException extends RuntimeException {
    public RolesNotFoundException(String message) {
        super(message);
    }
}

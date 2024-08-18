package org.example.clinicservice.exceptions.scheduleExceptions;

public class ScheduleAlreadyExistsException extends RuntimeException {
    public ScheduleAlreadyExistsException(String message) {
        super(message);
    }
}

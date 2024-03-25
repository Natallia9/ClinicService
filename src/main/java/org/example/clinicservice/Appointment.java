package org.example.clinicservice;

import org.example.clinicservice.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    private UUID appointmentId;
    private Specialist specialist;
    private Patient patient;
    private LocalDateTime dateTime;
    private Status status;

}

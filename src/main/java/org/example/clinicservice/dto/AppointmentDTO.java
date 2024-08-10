package org.example.clinicservice.dto;

import lombok.Data;
import org.example.clinicservice.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class AppointmentDTO {
    private UUID appointmentId;
    private String nameAppointment;
    private UUID specialistId;
    private UUID patientId;
    private LocalDateTime dateTime;
    private Status status;
}

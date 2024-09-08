package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents an appointment between a patient and a specialist.")
public class Appointment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "appointment_id")
    @Schema(description = "Unique identifier of the appointment", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID appointmentId;

    @Column(name = "name_of_the_doctor_appointment")
    @Schema(description = "Name or description of the doctor's appointment", example = "General Consultation")
    private String nameAppointment;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id")
    @Schema(description = "The specialist assigned to the appointment")
    private Specialist specialist;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @Schema(description = "The patient for the appointment")
    private Patient patient;

    @Column(name = "date_time")
    @Schema(description = "Date and time of the appointment", example = "2024-09-07T10:00:00")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Schema(description = "Current status of the appointment", example = "SCHEDULED")
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(appointmentId, that.appointmentId) && Objects.equals(nameAppointment, that.nameAppointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, nameAppointment);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", nameAppointment='" + nameAppointment + '\'' +
                ", specialist=" + specialist +
                ", patient=" + patient +
                ", dateTime=" + dateTime +
                ", status=" + status +
                '}';
    }
}

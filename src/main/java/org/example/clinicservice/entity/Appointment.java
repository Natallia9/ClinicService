package org.example.clinicservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.enums.Status;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id")
    private UUID appointmentId;

    @Column(name = "name_of_the_doctor's_appointment")
    private String nameAppointment;

    @OneToOne
    @JoinColumn(name = "specialist")
    private Specialist specialist;

    @OneToOne
    @JoinColumn(name = "patient")
    private Patient patient;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
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
}

package org.example.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Represents a doctor's work schedule in the system.")
public class Schedule {

    /**
     * Unique identifier of the schedule.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "schedule_id")
    @Schema(description = "Unique identifier of the schedule")
    private UUID scheduleId;

    /**
     * Specialist associated with the schedule.
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id")
    @Schema(description = "The doctor associated with the schedule")
    private Specialist doctor;

    /**
     * Working days in the schedule.
     */
    @ElementCollection
    @CollectionTable(name = "working_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "day")
    @Enumerated(EnumType.STRING)
    @Schema(description = "The working days of the doctor", example = "[MONDAY, WEDNESDAY, FRIDAY]")
    private List<DayOfWeek> workingDays;

    /**
     * Start time of the working schedule.
     */
    @Column(name = "start_time")
    @Schema(description = "Start time of the schedule", example = "09:00")
    private LocalTime startTime;

    /**
     * End time of the working schedule.
     */
    @Column(name = "end_time")
    @Schema(description = "End time of the schedule", example = "17:00")
    private LocalTime endTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(scheduleId, schedule.scheduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", doctor=" + doctor +
                ", workingDays=" + workingDays +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

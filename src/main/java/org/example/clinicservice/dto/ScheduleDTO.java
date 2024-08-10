package org.example.clinicservice.dto;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Data
public class ScheduleDTO {

    private UUID scheduleId;
    private UUID doctorId;
    private List<DayOfWeek> workingDays;
    private LocalTime startTime;
    private LocalTime endTime;
}

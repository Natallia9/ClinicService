package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {

    Schedule getScheduleById(UUID scheduleId);
    List<Schedule> getSchedulesByDoctorId(UUID doctorId);
    List<Schedule> getSchedulesByWorkingDay(DayOfWeek dayOfWeek);
    List<Schedule> getSchedulesByStartTimeBetween(LocalTime startTime, LocalTime endTime);
    List<Schedule> getSchedulesByDoctorIdAndWorkingDay(UUID doctorId, DayOfWeek dayOfWeek);
    List<Schedule> getSchedulesByDoctorIdAndStartTimeBetween(UUID doctorId, LocalTime startTime, LocalTime endTime);
    void saveSchedule(Schedule schedule);
    void deleteSchedule(UUID scheduleId);
}

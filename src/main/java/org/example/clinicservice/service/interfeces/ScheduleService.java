package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Schedule;
import org.example.clinicservice.entity.Specialist;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ScheduleService {

    Schedule getScheduleById(UUID scheduleId);
    List<Schedule> getSchedulesByDoctor(UUID specialist);
    Map<UUID, List<DayOfWeek>> getSchedulesByWorkingDay(DayOfWeek dayOfWeek);
    List<Schedule> getSchedulesByDoctorIdAndWorkingDay(UUID doctorId, DayOfWeek dayOfWeek);
    void saveSchedule(Schedule schedule);
    void deleteSchedule(UUID scheduleId);
}

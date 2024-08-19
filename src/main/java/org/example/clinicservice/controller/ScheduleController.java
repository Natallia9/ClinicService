package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Schedule;
import org.example.clinicservice.service.interfeces.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public Schedule getScheduleById(@PathVariable UUID scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    @GetMapping("/doctor/{specialistId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Schedule> getSchedulesByDoctor(@PathVariable UUID specialistId) {
        return scheduleService.getSchedulesByDoctor(specialistId);
    }

    @GetMapping("/working-day")
    @ResponseStatus(HttpStatus.OK)
    public Map<UUID, List<DayOfWeek>> getSchedulesByWorkingDay(@RequestParam DayOfWeek dayOfWeek) {
        return scheduleService.getSchedulesByWorkingDay(dayOfWeek);
    }

    @GetMapping("/doctor/{doctorId}/day")
    @ResponseStatus(HttpStatus.OK)
    public List<Schedule> getSchedulesByDoctorIdAndWorkingDay(
            @PathVariable UUID doctorId,
            @RequestParam DayOfWeek dayOfWeek) {
        return scheduleService.getSchedulesByDoctorIdAndWorkingDay(doctorId, dayOfWeek);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSchedule(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable UUID scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }
}




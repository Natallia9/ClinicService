package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Schedule;
import org.example.clinicservice.repository.ScheduleRepository;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule getScheduleById(UUID scheduleId) {

        return scheduleRepository.findByScheduleId(scheduleId);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorId(UUID doctorId) {

        return scheduleRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Schedule> getSchedulesByWorkingDay(DayOfWeek dayOfWeek) {

        return scheduleRepository.findByWorkingDaysContaining(dayOfWeek);
    }

    @Override
    public List<Schedule> getSchedulesByStartTimeBetween(LocalTime startTime, LocalTime endTime) {

        return scheduleRepository.findByStartTimeBetweenAndEndTime(startTime, endTime);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorIdAndWorkingDay(UUID doctorId, DayOfWeek dayOfWeek) {

        return scheduleRepository.findByDoctorIdAndWorkingDaysContaining(doctorId, dayOfWeek);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorIdAndStartTimeBetween(UUID doctorId, LocalTime startTime, LocalTime endTime) {
        return scheduleRepository.findByDoctorIdAndStartTimeBetween(doctorId, startTime, endTime);
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(UUID scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}

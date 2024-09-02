package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Schedule;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.scheduleExceptions.ScheduleNotFoundException;
import org.example.clinicservice.repository.ScheduleRepository;
import org.example.clinicservice.service.interfeces.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule getScheduleById(UUID scheduleId) {
        return scheduleRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException(ErrorMessage.SCHEDULE_NOT_FOUND));
    }

    @Override
    public List<Schedule> getSchedulesByDoctor(UUID specialistId) {
        List<Schedule> schedules = scheduleRepository.findBySpecialistId(specialistId);

        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException("No schedules found for specialist with ID " + specialistId);
        }

        return schedules;
    }

    @Override
    public Map<UUID, List<DayOfWeek>> getSchedulesByWorkingDay(DayOfWeek dayOfWeek) {
        List<Schedule> schedules = scheduleRepository.findByWorkingDaysContaining(dayOfWeek);

        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException(ErrorMessage.SCHEDULE_NOT_FOUND_FOR_DAY + dayOfWeek);
        }

        return schedules.stream()
                .collect(Collectors.toMap(
                        schedule -> schedule.getDoctor().getSpecialistId(),
                        Schedule::getWorkingDays
                ));
    }

    @Override
    public List<Schedule> getSchedulesByDoctorIdAndWorkingDay(UUID doctorId, DayOfWeek dayOfWeek) {
        List<Schedule> schedules = scheduleRepository.findByDoctorIdAndWorkingDaysContaining(doctorId, dayOfWeek);

        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException(ErrorMessage.SCHEDULE_NOT_FOUND_FOR_DOCTOR_AND_DAY);
        }
        return schedules;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(UUID scheduleId) {

        Schedule scheduleById = getScheduleById(scheduleId);
        scheduleRepository.deleteById(scheduleById.getScheduleId());
    }
}


package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    Schedule findByScheduleId(UUID scheduleId);
    List<Schedule> findByDoctorId(UUID doctorId);
    List<Schedule> findByWorkingDaysContaining(DayOfWeek dayOfWeek);
    List<Schedule> findByStartTimeBetweenAndEndTime(LocalTime startTime, LocalTime endTime);
    List<Schedule> findByDoctorIdAndWorkingDaysContaining(UUID doctorId, DayOfWeek dayOfWeek);
    List<Schedule> findByDoctorIdAndStartTimeBetween(UUID doctorId, LocalTime startTime, LocalTime endTime);
}

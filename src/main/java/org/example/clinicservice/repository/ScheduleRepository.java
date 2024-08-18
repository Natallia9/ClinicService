package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Schedule;
import org.example.clinicservice.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    Schedule findByScheduleId(UUID scheduleId);
    List<Schedule> findBySpecialistId(UUID specialistId);
    List<Schedule> findByWorkingDaysContaining(DayOfWeek dayOfWeek);
    boolean existsByDoctorAndWorkingDays(Specialist doctor, List<DayOfWeek> dayOfWeek);
    List<Schedule> findByDoctorIdAndWorkingDaysContaining(UUID doctorId, DayOfWeek dayOfWeek);

}

package org.example.clinicservice.controller;

import org.example.clinicservice.entity.Schedule;
import org.example.clinicservice.service.interfeces.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    private final UUID scheduleId = UUID.randomUUID();

    @Test
    void testGetScheduleById() throws Exception {
        Schedule schedule = new Schedule();

        when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);

        mockMvc.perform(get("/api/schedules/" + scheduleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetSchedulesByDoctor() throws Exception {
        UUID specialistId = UUID.randomUUID();
        List<Schedule> schedules = List.of(new Schedule());

        when(scheduleService.getSchedulesByDoctor(specialistId)).thenReturn(schedules);

        mockMvc.perform(get("/api/schedules/doctor/" + specialistId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetSchedulesByWorkingDay() throws Exception {
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        Map<UUID, List<DayOfWeek>> schedulesByDay = Map.of(UUID.randomUUID(), List.of(dayOfWeek));

        when(scheduleService.getSchedulesByWorkingDay(dayOfWeek)).thenReturn(schedulesByDay);

        mockMvc.perform(get("/api/schedules/working-day")
                        .param("dayOfWeek", "MONDAY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testGetSchedulesByDoctorIdAndWorkingDay() throws Exception {
        UUID doctorId = UUID.randomUUID();
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        List<Schedule> schedules = List.of(new Schedule());

        when(scheduleService.getSchedulesByDoctorIdAndWorkingDay(doctorId, dayOfWeek)).thenReturn(schedules);

        mockMvc.perform(get("/api/schedules/doctor/" + doctorId + "/day")
                        .param("dayOfWeek", "MONDAY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testSaveSchedule() throws Exception {
        Schedule schedule = new Schedule();

        doNothing().when(scheduleService).saveSchedule(schedule);

        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"scheduleId\": \"550e8400-e29b-41d4-a716-446655440000\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteSchedule() throws Exception {
        doNothing().when(scheduleService).deleteSchedule(scheduleId);

        mockMvc.perform(delete("/api/schedules/" + scheduleId))
                .andExpect(status().isNoContent());
    }
}

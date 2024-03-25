package org.example.clinicservice;

import org.example.clinicservice.entity.enums.Department;

import java.util.List;
import java.util.UUID;

public class Specialist {
    private UUID specialistId;
    private String field;
    private String qualification;
    private double experience;
    private double workingHours;
    private String contactInformation;
    private boolean	availability;
    private String languagesSpoken;
    private Department department;
    private List<Appointment> appointments;

}

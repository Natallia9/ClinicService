package org.example.clinicservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PatientDTO {

    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String age;
    private String sex;
    private String address;
    private String emerg;
}

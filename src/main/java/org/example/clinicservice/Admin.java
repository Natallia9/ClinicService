package org.example.clinicservice;

import org.example.clinicservice.entity.enums.AdminRole;

import java.util.List;
import java.util.UUID;

public class Admin {
    private UUID adminId;
    private AdminRole adminRole;
    private List<User> admins;


}

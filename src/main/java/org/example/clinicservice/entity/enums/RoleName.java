package org.example.clinicservice.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {
    SPECIALIST,
    PATIENT,
    ADMIN;


    @Override
    public String getAuthority() {

        return name();
    }
}

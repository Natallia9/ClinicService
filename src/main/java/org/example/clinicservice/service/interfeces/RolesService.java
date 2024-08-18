package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.entity.enums.RoleName;

import java.util.Set;
import java.util.UUID;

public interface RolesService {

    Roles getRoleById(UUID roleId);
    Set<Roles> getRolesByUserId(UUID userId);
    void saveRole(Roles role);
    void deleteRole(UUID roleId);
}

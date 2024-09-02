package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.roleExceptions.RolesNotFoundException;
import org.example.clinicservice.repository.RolesRepository;
import org.example.clinicservice.service.interfeces.RolesService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public Roles getRoleById(UUID roleId) {
        return rolesRepository.findById(roleId)
                .orElseThrow(() -> new RolesNotFoundException(ErrorMessage.ROLE_NOT_FOUND));
    }

    @Override
    public Set<Roles> getRolesByUserId(UUID userId) {
        Set<Roles> roles = rolesRepository.findByUsersUserId(userId);
        if (roles.isEmpty()) {
            throw new RolesNotFoundException(ErrorMessage.ROLES_NOT_FOUND_FOR_USER);
        }
        return roles;
    }

    @Override
    public void saveRole(Roles role) {
        rolesRepository.save(role);
    }

    @Override
    public void deleteRole(UUID roleId) {
        getRoleById(roleId);
        rolesRepository.deleteById(roleId);
    }

}

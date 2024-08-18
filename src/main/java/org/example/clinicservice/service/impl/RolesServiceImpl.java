package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.roleExceptions.RoleAlreadyExistsException;
import org.example.clinicservice.exceptions.roleExceptions.RoleSaveException;
import org.example.clinicservice.exceptions.roleExceptions.RolesNotFoundException;
import org.example.clinicservice.exceptions.roleExceptions.RolesRetrievalException;
import org.example.clinicservice.exceptions.userExceptions.UserNotFoundException;
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

        try {
            Roles role = rolesRepository.findByRoleId(roleId);
            if(roleId == null){
                throw new RolesNotFoundException(ErrorMessage.ROLE_NOT_FOUND);
            }
            return role;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving role with id " + roleId, e);
        }
    }


    @Override
    public Set<Roles> getRolesByUserId(UUID userId) {

        if (userId == null) {
            throw new UserNotFoundException(ErrorMessage.INVALID_USER_ID);
        }

        try {
            Set<Roles> roles = rolesRepository.findByUsersUserId(userId);
            if (roles.isEmpty()) {
                throw new RolesNotFoundException(ErrorMessage.ROLES_NOT_FOUND_FOR_USER);
            }
            return roles;
        } catch (Exception e) {
            throw new RolesRetrievalException("Failed to retrieve roles from the repository ", e);
        }
    }

    @Override
    public void saveRole(Roles role) {

        if (rolesRepository.existsById(role.getRoleId())) {
            throw new RoleAlreadyExistsException(ErrorMessage.ROLE_ALREADY_EXISTS);
        }
        try {
            rolesRepository.save(role);
        } catch (Exception e) {
            throw new RoleSaveException("Failed to save role due to an unexpected error: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteRole(UUID roleId) {

        if (!rolesRepository.existsById(roleId)) {
            throw new RolesNotFoundException(ErrorMessage.ROLES_NOT_FOUND);
        }

        try {
            rolesRepository.deleteById(roleId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete role with ID: " + roleId, e);
        }
    }
}


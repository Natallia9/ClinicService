package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.entity.enums.RoleName;
import org.example.clinicservice.repository.RolesRepository;
import org.example.clinicservice.repository.SpecialistRepository;
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

        return rolesRepository.findByRoleId(roleId);
    }

    @Override
    public Roles getRoleByName(RoleName roleName) {

        return rolesRepository.findByRoleName(roleName);
    }

    @Override
    public Set<Roles> getRolesByAuthorityId(UUID authorityId) {

        return rolesRepository.findByAuthoritiesAuthorityId(authorityId);
    }

    @Override
    public Set<Roles> getRolesByUserId(UUID userId) {

        return rolesRepository.findByUsersUserId(userId);
    }

    @Override
    public void saveRole(Roles role) {
        rolesRepository.save(role);
    }

    @Override
    public void deleteRole(UUID roleId) {
        rolesRepository.deleteById(roleId);
    }
}

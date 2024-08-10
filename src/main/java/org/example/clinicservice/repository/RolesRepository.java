package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID> {
    Roles findByRoleId(UUID roleId);
    Roles findByRoleName(RoleName roleName);
    Set<Roles> findByAuthoritiesAuthorityId(UUID authorityId);
    Set<Roles> findByUsersUserId(UUID userId);
}
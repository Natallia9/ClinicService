package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.service.interfeces.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @GetMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public Roles getRoleById(@PathVariable UUID roleId) {
        return rolesService.getRoleById(roleId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Roles> getRolesByUserId(@PathVariable UUID userId) {
        return rolesService.getRolesByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody Roles role) {
        rolesService.saveRole(role);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable UUID roleId) {
        rolesService.deleteRole(roleId);
    }
}



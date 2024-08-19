package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.SystemOwner;
import org.example.clinicservice.service.interfeces.SystemOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/system-owners")
@RequiredArgsConstructor
public class SystemOwnerController {

    private final SystemOwnerService systemOwnerService;

    @GetMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    public SystemOwner getSystemOwnerById(@PathVariable UUID ownerId) {
        SystemOwner systemOwner = systemOwnerService.getSystemOwnerById(ownerId);
        return systemOwner;
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public SystemOwner getSystemOwnerByEmail(@RequestParam String email) {
        SystemOwner systemOwner = systemOwnerService.getSystemOwnerByEmail(email);
        return systemOwner;
    }

    @GetMapping("/phone")
    @ResponseStatus(HttpStatus.OK)
    public SystemOwner getSystemOwnerByPhoneNumber(@RequestParam String phoneNumber) {
        SystemOwner systemOwner = systemOwnerService.findByPhoneNumber(phoneNumber);
        return systemOwner;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSystemOwner(@RequestBody SystemOwner systemOwner) {
        SystemOwner owner = systemOwnerService.saveSystemOwner(systemOwner);
        systemOwnerService.saveSystemOwner(owner);
    }

    @DeleteMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSystemOwner(@PathVariable UUID ownerId) {
        systemOwnerService.deleteSystemOwner(ownerId);
    }
}

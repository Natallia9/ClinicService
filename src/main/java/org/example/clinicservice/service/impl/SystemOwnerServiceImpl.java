package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.SystemOwner;
import org.example.clinicservice.exceptions.EmailNotFoundExсeption;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.systemOwnerExceptions.SystemOwnerNotFoundException;
import org.example.clinicservice.exceptions.PhoneNumberNotFoundException;
import org.example.clinicservice.repository.SystemOwnerRepository;
import org.example.clinicservice.service.interfeces.SystemOwnerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SystemOwnerServiceImpl implements SystemOwnerService {

    private final SystemOwnerRepository systemOwnerRepository;

    @Override
    public SystemOwner getSystemOwnerById(UUID ownerId) {
        return systemOwnerRepository.findByOwnerId(ownerId)
                .orElseThrow(() ->
                        new SystemOwnerNotFoundException(ErrorMessage.SYSTEM_OWNER_NOT_FOUND));
    }

    @Override
    public SystemOwner getSystemOwnerByEmail(String email) {
        return systemOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundExсeption(ErrorMessage.EMAIL_DOES_NOT_EXIST));
    }

    @Override
    public SystemOwner findByPhoneNumber(String phoneNumber) {
        return systemOwnerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new PhoneNumberNotFoundException(ErrorMessage.PHONE_NUMBER_DOES_NOT_EXIST));
    }

    @Override
    public SystemOwner saveSystemOwner(SystemOwner systemOwner) {

        return systemOwnerRepository.save(systemOwner);

    }

    @Override
    public void deleteSystemOwner(UUID ownerId) {
        SystemOwner systemOwnerById = getSystemOwnerById(ownerId);
        systemOwnerRepository.deleteById(systemOwnerById.getOwnerId());
    }
}



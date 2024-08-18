package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.SystemOwner;
import org.example.clinicservice.exceptions.userExceptions.EmailNotFoundExсeption;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.userExceptions.InvalidIdException;
import org.example.clinicservice.exceptions.systemOwnerExceptions.SystemOwnerExistsException;
import org.example.clinicservice.exceptions.systemOwnerExceptions.SystemOwnerNotFoundException;
import org.example.clinicservice.exceptions.userExceptions.InvalidPhoneNumberException;
import org.example.clinicservice.exceptions.userExceptions.PhoneNumberNotFoundException;
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

        try {
            SystemOwner systemOwner = systemOwnerRepository.findByOwnerId(ownerId);
            if(systemOwner == null){
                throw new SystemOwnerNotFoundException(ErrorMessage.SYSTEM_OWNER_NOT_FOUND);
            }
            return systemOwner;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving System Owner with id " + ownerId, e);
        }
    }

    @Override
    public SystemOwner getSystemOwnerByEmail(String email) {
        try {
            SystemOwner systemOwner = systemOwnerRepository.findByEmail(email);
            if(systemOwner == null){
                throw new EmailNotFoundExсeption(ErrorMessage.EMAIL_DOES_NOT_EXIST);
            }
            return systemOwner;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while searching for system owner by email: " + email, e);
        }
    }

    @Override
    public SystemOwner findByPhoneNumber(String phoneNumber) {
        try {
            SystemOwner systemOwner = systemOwnerRepository.findByPhoneNumber(phoneNumber);

            if (systemOwner == null) {
                throw new PhoneNumberNotFoundException(ErrorMessage.PHONE_NUMBER_DOES_NOT_EXIST);
            }

            return systemOwner;
        } catch (IllegalArgumentException e) {
            throw new InvalidPhoneNumberException("Invalid phone number provided: " + e);
        }
    }

    @Override
    public void saveSystemOwner(SystemOwner systemOwner) {

        if (systemOwnerRepository.existsById(systemOwner.getOwnerId())) {
            throw new SystemOwnerExistsException(ErrorMessage.SYSTEM_OWNER_WITH_ID_EXIST);
        }
        try {
            systemOwnerRepository.save(systemOwner);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save system owner due to invalid argument: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteSystemOwner(UUID ownerId) {

        try {
            if (!systemOwnerRepository.existsById(ownerId)) {
                throw new SystemOwnerNotFoundException(ErrorMessage.USER_WITH_ID_NOT_EXIST);
            }
            systemOwnerRepository.deleteById(ownerId);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdException(ErrorMessage.INVALID_USER_ID);
        }
    }
}


package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.SystemOwner;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.repository.SystemOwnerRepository;
import org.example.clinicservice.service.interfeces.SystemOwnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SystemOwnerServiceImpl implements SystemOwnerService {

    private final SystemOwnerRepository systemOwnerRepository;

    @Override
    public SystemOwner getSystemOwnerById(UUID ownerId) {

        return systemOwnerRepository.findByOwnerId(ownerId);
    }

    @Override
    public SystemOwner getSystemOwnerByEmail(String email) {

        return systemOwnerRepository.findByEmail(email);
    }

    @Override
    public SystemOwner getSystemOwnerByPhoneNumber(String phoneNumber) {

        return systemOwnerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<SystemOwner> getSystemOwnerByFirstNameAndLastName(String firstName, String lastName) {

        return systemOwnerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<SystemOwner> getSystemOwnersByEmailContaining(String email) {

        return systemOwnerRepository.findByEmailContaining(email);
    }

    @Override
    public void saveSystemOwner(SystemOwner systemOwner) {

        systemOwnerRepository.save(systemOwner);
    }

    @Override
    public void deleteSystemOwner(UUID ownerId) {

        systemOwnerRepository.deleteById(ownerId);
    }
}

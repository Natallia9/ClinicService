package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.SystemOwner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SystemOwnerService {

    SystemOwner getSystemOwnerById(UUID ownerId);
    SystemOwner getSystemOwnerByEmail(String email);
    SystemOwner getSystemOwnerByPhoneNumber(String phoneNumber);
    List<SystemOwner> getSystemOwnerByFirstNameAndLastName(String firstName, String lastName);
    List<SystemOwner> getSystemOwnersByEmailContaining(String email);
    void saveSystemOwner(SystemOwner systemOwner);
    void deleteSystemOwner(UUID ownerId);
}

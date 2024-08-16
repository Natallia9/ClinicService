package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.SystemOwner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SystemOwnerService {

//    SystemOwner addSystemOwner(SystemOwner systemOwner);
    SystemOwner getSystemOwnerById(UUID ownerId);
    SystemOwner getSystemOwnerByEmail(String email);
    SystemOwner findByPhoneNumber(String phoneNumber);
    void saveSystemOwner(SystemOwner systemOwner);
    void deleteSystemOwner(UUID ownerId);
}

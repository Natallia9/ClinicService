package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.entity.SystemOwner;

import java.util.UUID;

public interface SystemOwnerService {

//    SystemOwner addSystemOwner(SystemOwner systemOwner);
    SystemOwner getSystemOwnerById(UUID ownerId);
    SystemOwner getSystemOwnerByEmail(String email);
    SystemOwner findByPhoneNumber(String phoneNumber);
    SystemOwner saveSystemOwner(SystemOwner systemOwner);
    void deleteSystemOwner(UUID ownerId);
}

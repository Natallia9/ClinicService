package org.example.clinicservice.repository;

import org.example.clinicservice.entity.SystemOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SystemOwnerRepository extends JpaRepository<SystemOwner, UUID> {

    SystemOwner findByOwnerId(UUID ownerId);
    SystemOwner findByEmail(String email);
    SystemOwner findByPhoneNumber(String phoneNumber);

}

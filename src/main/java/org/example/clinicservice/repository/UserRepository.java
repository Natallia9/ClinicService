package org.example.clinicservice.repository;

import org.example.clinicservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUserId(UUID userId);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    User findByEmail(String email);
    List<User> updateUserByUserId(UUID userId);

}

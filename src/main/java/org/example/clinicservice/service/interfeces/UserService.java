package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.UUID;

public interface UserService {

    User addUser(UserDTO userDTO);
    User getUserById(UUID userId);
    List<UserDTO> getUsersByFirstNameAndLastName(String firstName, String lastName);
    User getUserByEmail(String email);
    void deleteUser(UUID userId);
}

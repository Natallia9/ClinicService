package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.repository.UserRepository;
import org.example.clinicservice.service.interfeces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User addUser(UserDTO userDTO) {
        User user = convertToUser(userDTO);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserType(userDTO.getUserType());
        user.setRoles(userDTO.getRoles());
        return user;
    }
}

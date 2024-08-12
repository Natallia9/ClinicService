package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.userExeptions.EmailNotFoundExaption;
import org.example.clinicservice.exceptions.userExeptions.UserExistsException;
import org.example.clinicservice.exceptions.userExeptions.UserNotFoundException;
import org.example.clinicservice.repository.UserRepository;
import org.example.clinicservice.service.interfeces.UserService;
import org.example.clinicservice.transformers.UserTransformer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public User addUser(UserDTO userDTO) {

        User user = UserTransformer.convertToUser(userDTO);
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserExistsException(ErrorMessage.USER_WITH_EMAIL_EXISTS);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                throw new UserNotFoundException("User with id " + userId + " not found");
            }
            return user;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user ID provided: " + userId, e);
        }
    }

    @Override
    public List<UserDTO> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        List<User> users = userRepository.findByFirstNameAndLastName(firstName, lastName);
        if (users.isEmpty()) {
            if (userRepository.findByFirstName(firstName).isEmpty()) {
                throw new UserNotFoundException("No users found with the first name: " + firstName);
            }
            if (userRepository.findByLastName(lastName).isEmpty()) {
                throw new UserNotFoundException("No users found with the last name: " + lastName);
            }
        }
        return users.stream()
                .map(UserTransformer::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new EmailNotFoundExaption(ErrorMessage.EMAIL_DOES_NOT_EXIST);
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while searching for user by email: " + email, e);
        }
    }

    @Override
    public void deleteUser(UUID userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(ErrorMessage.ID_NOT_FOUND);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user ID", e);
        }
    }
}

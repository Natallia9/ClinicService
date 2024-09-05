package org.example.clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.EmailNotFoundExсeption;
import org.example.clinicservice.exceptions.userExceptions.UserExistsException;
import org.example.clinicservice.exceptions.userExceptions.UserNotFoundException;
import org.example.clinicservice.repository.UserRepository;
import org.example.clinicservice.service.interfeces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public User addUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserExistsException(ErrorMessage.USER_WITH_EMAIL_EXISTS);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID userId) {

           return userRepository.findByUserId(userId)
                   .orElseThrow(() ->
                           new UserNotFoundException("User with id " + userId + " not found"));
        }



    @Override
    public List<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        List<User> users = userRepository.findByFirstNameAndLastName(firstName, lastName);
        if (users.isEmpty()) {
            if (userRepository.findByFirstName(firstName).isEmpty()) {
                throw new UserNotFoundException("No users found with the first name: " + firstName);
            }
            if (userRepository.findByLastName(lastName).isEmpty()) {
                throw new UserNotFoundException("No users found with the last name: " + lastName);
            }
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EmailNotFoundExсeption(ErrorMessage.EMAIL_DOES_NOT_EXIST));
    }

    @Override
    public void deleteUser(UUID userId) {
        User userById = getUserById(userId);
        userRepository.deleteById(userById.getUserId());

    }
}


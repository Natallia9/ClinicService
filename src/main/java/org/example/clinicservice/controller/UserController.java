package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.userExceptions.EmailNotFoundExсeption;
import org.example.clinicservice.exceptions.userExceptions.UserExistsException;
import org.example.clinicservice.exceptions.userExceptions.UserNotFoundException;
import org.example.clinicservice.service.interfeces.UserService;
import org.example.clinicservice.transformers.UserTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return UserTransformer.convertToUserDTO(savedUser);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable UUID userId) {
        User user = userService.getUserById(userId);
        return UserTransformer.convertToUserDTO(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsersByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        List<User> users = userService.getUsersByFirstNameAndLastName(firstName, lastName);
        return users.stream()
                .map(UserTransformer::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return UserTransformer.convertToUserDTO(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }
}




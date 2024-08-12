package org.example.clinicservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.exceptions.ErrorMessage;
import org.example.clinicservice.exceptions.userExeptions.EmailNotFoundExaption;
import org.example.clinicservice.exceptions.userExeptions.UserExistsException;
import org.example.clinicservice.exceptions.userExeptions.UserNotFoundException;
import org.example.clinicservice.service.interfeces.UserService;
import org.example.clinicservice.transformers.UserTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            User user = userService.addUser(userDTO);
            return UserTransformer.convertToUserDTO(user);
        } catch (IllegalArgumentException e) {
            throw new UserExistsException(ErrorMessage.USER_WITH_EMAIL_EXISTS);
        }
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
        try {
            List<UserDTO> users = userService.getUsersByFirstNameAndLastName(firstName, lastName);
            return users;
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input parameters");
        }
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByEmail(@RequestParam String email) {
        try {
            User user = userService.getUserByEmail(email);
            return UserTransformer.convertToUserDTO(user);
        } catch (EmailNotFoundExaption ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        try {
            userService.deleteUser(userId);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}



package org.example.clinicservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.service.interfeces.UserService;
import org.example.clinicservice.transformers.UserTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        User user = userService.addUser(userDTO);
        return UserTransformer.convertToUserDTO(user);
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
        return userService.getUsersByFirstNameAndLastName(firstName, lastName);
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



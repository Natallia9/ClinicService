package org.example.clinicservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;
import org.example.clinicservice.mapper.UserMapper;
import org.example.clinicservice.service.interfeces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody @Valid UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userService.addUser(user);
        return userMapper.toDto(savedUser);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable UUID userId) {
        User user = userService.getUserById(userId);
        return userMapper.toDto(user);
    }

    @GetMapping("/getUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsersByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        List<User> users = userService.getUsersByFirstNameAndLastName(firstName, lastName);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }
}




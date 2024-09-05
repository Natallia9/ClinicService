package org.example.clinicservice.security;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.JwtAuthenticationResponse;
import org.example.clinicservice.dto.SignInRequest;
import org.example.clinicservice.dto.SignUpRequest;
import org.example.clinicservice.entity.Roles;
import org.example.clinicservice.entity.enums.RoleName;
import org.example.clinicservice.service.interfeces.RolesService;
import org.example.clinicservice.service.interfeces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final RolesService rolesService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        Set<Roles> roles = Set.of(
                rolesService.getRoleByName(RoleName.SPECIALIST.name()),
                rolesService.getRoleByName(RoleName.PATIENT.name()),
                rolesService.getRoleByName(RoleName.ADMIN.name())
        );

        org.example.clinicservice.entity.User entityUser = new org.example.clinicservice.entity.User();
        entityUser.setUserName(request.getUsername());
        entityUser.setPassword(passwordEncoder.encode(request.getPassword()));
        entityUser.setEmail(request.getEmail());
        entityUser.setRoles(roles);

        userService.addUser(entityUser);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                entityUser.getUserName(),
                entityUser.getPassword(),
                new ArrayList<>()
        );

        String jwt = jwtService.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
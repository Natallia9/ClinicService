package org.example.clinicservice.mapper;

import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface UserMapper {


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "userName", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "userType", source = "userType"),
            @Mapping(target = "roles", ignore = true)
    })
    User toEntity(UserDTO userDTO);

    @AfterMapping
    default void createdUser(@MappingTarget User user, UserDTO userDTO) {
        user.setUserId(user.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setPassword(setPassword(passwordEncoder.encode(userDTO.getPassword())));
        user.setEmail(userDTO.getEmail());
        user.setUserType(userDTO.getUserType());
        user.setRoles(userDTO.getRoles());
    }

    @Mappings({
            @Mapping(target = "lastName", source = "user.lastName"),
            @Mapping(target = "firstName", source = "user.firstName"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "userType", source = "user.userType"),
            @Mapping(target = "roles", source = "user.roles")
    })
    UserDTO toDto(User user);
}

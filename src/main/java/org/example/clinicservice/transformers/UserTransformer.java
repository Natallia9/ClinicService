package org.example.clinicservice.transformers;

import org.example.clinicservice.dto.UserDTO;
import org.example.clinicservice.entity.User;

public class UserTransformer {

    public static User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserType(userDTO.getUserType());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    public static UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}

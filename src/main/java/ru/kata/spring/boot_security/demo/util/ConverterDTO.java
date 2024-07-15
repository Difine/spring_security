package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;

@Component
public class ConverterDTO {

    RoleService roleService;

    @Autowired
    public ConverterDTO(RoleService roleService) {
        this.roleService = roleService;
    }

    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        for (RoleDTO roleDTO : userDTO.getRoles()) {
            user.addRole(convertToRole(roleDTO));
        }

        return user;
    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        for (Role role : user.getRoles()) {
            userDTO.addRole(convertToRoleDTO(role));
        }
        return userDTO;
    }

    public RoleDTO convertToRoleDTO(Role role) {
        return new RoleDTO(role.getName());
    }

    public Role convertToRole(RoleDTO roleDTO) {
        return roleService.findByName(roleDTO.getName());
    }
}

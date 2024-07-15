package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UsersRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role role : roleService.findAll()) {
            roleDTOList.add(convertToRoleDTO(role));
        }
        return roleDTOList;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            userDTOList.add(convertToUserDTO(user));
        }
        return userDTOList;
    }

    @GetMapping("/users/userinfo")
    public UserDTO showUserInfo(Principal principal) {
        return convertToUserDTO(userService.findByUsername(principal.getName()).get());
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.saveUser(convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/users")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private User convertToUser(UserDTO userDTO) {
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

    private UserDTO convertToUserDTO(User user) {
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

    private RoleDTO convertToRoleDTO(Role role) {
        return new RoleDTO(role.getName());
    }

    private Role convertToRole(RoleDTO roleDTO) {
        return roleService.findByName(roleDTO.getName());
    }

}

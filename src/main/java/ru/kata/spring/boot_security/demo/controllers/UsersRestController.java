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
import ru.kata.spring.boot_security.demo.util.ConverterDTO;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final ConverterDTO converterDTO;

    @Autowired
    public UsersRestController(UserService userService, RoleService roleService, ConverterDTO converterDTO) {
        this.userService = userService;
        this.roleService = roleService;
        this.converterDTO = converterDTO;
    }

    @GetMapping("/roles")
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role role : roleService.findAll()) {
            roleDTOList.add(converterDTO.convertToRoleDTO(role));
        }
        return roleDTOList;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            userDTOList.add(converterDTO.convertToUserDTO(user));
        }
        return userDTOList;
    }

    @GetMapping("/users/userinfo")
    public UserDTO showUserInfo(Principal principal) {
        return converterDTO.convertToUserDTO(userService.findByUsername(principal.getName()).get());
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.saveUser(converterDTO.convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/users")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(converterDTO.convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

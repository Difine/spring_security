package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UsersController {

    @GetMapping("user")
    public String showUserInfo() {
        return "user";
    }

    @GetMapping("admin")
    public String showAllUsers() {
        return "all-users";
    }
}

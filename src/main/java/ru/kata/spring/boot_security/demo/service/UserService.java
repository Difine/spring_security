package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    Optional<User> findByUsername(String username);

    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);
}

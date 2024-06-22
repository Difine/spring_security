package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(long id);

    Optional<User> getUserByUsername(String username);

    void saveUser(User user);

    void deleteUser(long id);

    void updateUser(User user);
}
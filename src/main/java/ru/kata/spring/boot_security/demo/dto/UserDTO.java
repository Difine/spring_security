package ru.kata.spring.boot_security.demo.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private Byte age;
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, Set<RoleDTO> roles, Byte age, String lastName, String name, String password, String username) {
        this.id = id;
        this.roles = roles;
        this.age = age;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public void addRole(RoleDTO role) {
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(name, userDTO.name) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(age, userDTO.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, lastName, age);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

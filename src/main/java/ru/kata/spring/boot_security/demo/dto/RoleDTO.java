package ru.kata.spring.boot_security.demo.dto;

import java.util.Objects;

public class RoleDTO {
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(name, roleDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

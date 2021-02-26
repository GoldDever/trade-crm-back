package ru.javamentor.dto.user;

import ru.javamentor.model.user.Role;

import java.util.Set;

/**
 * DTO для создания нового Manager
 */
public class ManagerPostDto extends UserDto {
    private String clientName;
    private Long manager_id;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Set<Role> roles;


    public ManagerPostDto() {
    }

    public ManagerPostDto(String clientName, String password, String username, String firstName, String lastName, String patronymic) {
        this.clientName = clientName;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public ManagerPostDto(String clientName, Long manager_id) {
        this.clientName = clientName;
        this.manager_id = manager_id;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getManager_id() {
        return manager_id;
    }

    public void setManager_id(Long manager_id) {
        this.manager_id = manager_id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public CharSequence setPassword() {
        return password;
    }


    public Integer setPassword(String encode, String firstName, String lastName, String patronymic, Set<Role> roles, String clientName) {
        return null;
    }


}

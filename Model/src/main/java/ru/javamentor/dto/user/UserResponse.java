package ru.javamentor.dto.user;

import lombok.Data;

@Data
public class UserResponse {

    public UserResponse() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserResponse(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    private String lastName;
    private String firstName;

}

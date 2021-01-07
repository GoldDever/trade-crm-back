package ru.javamentor.service;

import ru.javamentor.model.user.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
}

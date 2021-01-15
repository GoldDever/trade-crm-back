package ru.javamentor.service;

import ru.javamentor.model.user.User;

public interface UserService {

    User findByUsername(String userName);

    Boolean existUserByUsername(String userName);
}

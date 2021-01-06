package ru.javamentor.service;

import ru.javamentor.model.user.User;

import java.util.List;

public interface UserService {
    /*
    void create(User user);
//    List<User> readAllUsers();

    //void
    //void update(long id, User user);
    void update(Long id, User user);
//    void delete(User user);
    void delete(long id);
*/

//    User findUser(String name);
//    User findUserById(long id); //User user
    User findByUsername(String username);
//    List<User> userListByModelAndSeries(String model, int series);

}

package ru.javamentor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.model.user.User;
import javamentor.repository.UserRepository;

import java.util.List;

@Service//не проксируются, объявление бинами @Bean
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }
    /*@Transactional
    @Override
    public void create(User user) {
        updateAuthorityList(user);
        userDao.create(user);
    }



    @Transactional
    @Override
    public void update(Long id, User user) {
        User old = userDao.findUserById(id);
        old.setFirstName(user.getFirstName());
        old.setLastName(user.getLastName());
        old.setAge(user.getAge());
        old.setEmail(user.getEmail());
        old.setLogin(user.getLogin());
        old.setPassword(user.getPassword());
        old.setAuthorityList(user.getAuthorityList());
        updateAuthorityList(old);
        userDao.update(old);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Transactional//transactional javax?
    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }
*/
    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

  /*  private void updateAuthorityList(User user) {
        List<Authority> authorityList = new ArrayList<>();
        for (Authority authority : user.getAuthorityList()) {
            authorityList.add(authorityDao.findByAuthority(authority.getAuthority()));
        }
        user.setAuthorityList(authorityList);
    }*/
}

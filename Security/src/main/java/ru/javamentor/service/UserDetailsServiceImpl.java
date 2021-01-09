package ru.javamentor.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javamentor.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserService userService;

        public UserDetailsServiceImpl(UserService userService) {
            this.userService = userService;
        }

        /**
         *
         * @param username
         * @return
         * @throws UsernameNotFoundException
         */
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetails user = (UserDetails) userService.findByUsername(username);
            if (user == null) {
                throw new EntityNotFoundException("No user found with this name");
            }
            return user;
        }
}
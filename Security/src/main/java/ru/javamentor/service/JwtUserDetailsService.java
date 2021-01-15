package ru.javamentor.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

        public JwtUserDetailsService(UserService userService) {
            this.userService = userService;
        }

        /**
         * Метод получения UserDetail по username
         *
         * @param username - username
         * @return - UserDetails
         */

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userService.findByUsername(username);
        }
}
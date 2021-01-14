package ru.javamentor.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
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
            UserDetails user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("No userDetails found with this name");
            }
            return user;
        }
}
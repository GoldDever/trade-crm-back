package ru.javamentor.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javamentor.configuration.jwt.JwtUser;
import ru.javamentor.model.user.User;

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
            User user = userService.findByUsername(username);
            UserDetails userDetails = null;
            if (user == null) {
                throw new UsernameNotFoundException("No userDetails found with this name");
            } else {
                userDetails = new JwtUser(user);
            }
            return userDetails;
        }
}
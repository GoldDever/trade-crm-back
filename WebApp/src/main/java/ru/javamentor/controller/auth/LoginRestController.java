package ru.javamentor.controller.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.configuration.jwt.JwtProvider;
import ru.javamentor.dto.user.UserLoginDto;
import ru.javamentor.model.user.User;
import ru.javamentor.service.UserService;

@RestController
@RequestMapping("api/auth")
public class LoginRestController {

    /**
     * Контроллер для логина
     */

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtProvider jwtProvider;


    public LoginRestController(AuthenticationManager authenticationManager, UserService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/login")
    public ResponseEntity loginIntoApp(@RequestBody UserLoginDto userLoginDto) {
        try {
            String username = userLoginDto.getUsername();
            User user = userService.findByUsername(username);
            if (user.getUsername() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                String token = jwtProvider.generateJwt(userLoginDto.getRememberMe(),userLoginDto.getUsername(),userLoginDto.getPassword());
                HttpHeaders headers = new HttpHeaders();

                headers.add("login", username);
                headers.add("token", token);

                return new ResponseEntity<String>(headers, HttpStatus.OK);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
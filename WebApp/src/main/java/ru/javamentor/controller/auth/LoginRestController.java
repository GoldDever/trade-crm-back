package ru.javamentor.controller.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
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
    private final UserService userService;

    private final JwtProvider jwtProvider;

    public LoginRestController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity loginIntoApp(@RequestBody UserLoginDto userLoginDto) {
        try {
            User user = userService.findByUsername(userLoginDto.getUsername());
            if (user.getUsername() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                String token = jwtProvider.generateJwt(userLoginDto.getRememberMe(),userLoginDto.getUsername(),userLoginDto.getPassword());
                HttpHeaders headers = new HttpHeaders();
                headers.add("token", token);

                return new ResponseEntity<String>(headers, HttpStatus.OK);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
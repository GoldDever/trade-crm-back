package ru.javamentor.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.configuration.jwt.JwtProvider;
import ru.javamentor.dto.user.UserLoginDto;
import ru.javamentor.model.user.User;
import ru.javamentor.service.UserService;

@RestController
@RequestMapping("api/auth")
public class LoginRestController {

    private final UserService userService;

    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public LoginRestController(
            UserService userService,
            JwtProvider jwtProvider,
            BCryptPasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Метод возвращающий токен по логину и пароль
     * @param userLoginDto - модель содержащая логин и пароль
     * @return - токен либо ответ об ошибке
     */

    @PostMapping("/login")
    public ResponseEntity<String> loginIntoApp(@RequestBody UserLoginDto userLoginDto) {
        if (userService.existUserByUsername(userLoginDto.getUsername())) {
            User user = userService.findByUsername(userLoginDto.getUsername());
            if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
                final Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginDto.getUsername(),
                                userLoginDto.getPassword()
                        )
                );
                return ResponseEntity
                        .ok(jwtProvider
                                .generateJwt(authentication, userLoginDto.getRememberMe()));
            } else {
                return ResponseEntity.badRequest().body("Неверный пароль!");
            }
        } else {
            return ResponseEntity.badRequest().body("Пользователь с таким логином не найден");
        }
    }
}
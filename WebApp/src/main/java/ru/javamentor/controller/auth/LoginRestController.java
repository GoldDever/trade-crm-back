package ru.javamentor.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.UserLoginDto;

@RestController
@RequestMapping("api/auth")
public class LoginRestController {
    private final UserLoginDto userLoginDto;

    @Autowired
    public LoginRestController(UserLoginDto userLoginDto) {
        this.userLoginDto = userLoginDto;
    }

    /**
     * Контроллер для логина
     */
    @GetMapping("/login")
    public ResponseEntity<UserLoginDto> loginIntoApp(UserLoginDto userLoginDto) {
        if (userLoginDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
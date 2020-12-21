package ru.javamentor.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.dto.user.UserLoginDto;

@RestController
@RequestMapping("api/auth")
public class LoginRestController {
    /**
     * Контроллер для логина
     */
    @GetMapping("/login")
    public ResponseEntity<String> loginIntoApp(@RequestBody UserLoginDto userLoginDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
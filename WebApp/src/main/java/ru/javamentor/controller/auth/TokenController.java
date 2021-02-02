package ru.javamentor.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/token/")
public class TokenController {

    @GetMapping("roleByToken")
    public ResponseEntity<?> getRoleByToken() {
        return ResponseEntity.ok("");
    }
}

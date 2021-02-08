package ru.javamentor.controller.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.configuration.jwt.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/token/")
public class TokenController {

    private final HttpServletRequest httpServletRequest;
    private final JwtProvider jwtProvider;

    @Autowired
    public TokenController(HttpServletRequest httpServletRequest, JwtProvider jwtProvider) {
        this.httpServletRequest = httpServletRequest;
        this.jwtProvider = jwtProvider;
    }
    @GetMapping("roleByToken")
    public ResponseEntity<?> getRoleByToken() {
        return ResponseEntity.ok(jwtProvider.getRoleByToken(httpServletRequest));
    }
}

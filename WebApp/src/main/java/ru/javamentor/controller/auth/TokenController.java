package ru.javamentor.controller.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/token/")
public class TokenController {

    @Value("${jwt.authorization}")
    private String authorization;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.tokenIdentifier}")
    private String tokenIdentifier;

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public TokenController(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    @GetMapping("roleByToken")
    public ResponseEntity<?> getRoleByToken() {
        String token = httpServletRequest.getHeader(authorization);
        token = token.substring(tokenIdentifier.length());
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        List<String> listAuthorizations = (ArrayList<String>)claims.get(authorization);
        return ResponseEntity.ok(listAuthorizations);
    }
}

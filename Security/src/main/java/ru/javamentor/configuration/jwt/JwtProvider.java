package ru.javamentor.configuration.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.util.StringUtils.hasText;


@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    @Value("${jwt.expirationHours)")
    private String jwtExpirationHours;

    @Value("${jwt.expirationIfRememberedYears)")
    private String jwtExpirationIfRememberedYears;

    @Value("$(jwt.authorization")
    private String authorization;

    @Value("$(jwt.tokenIdentifier")
    private String tokenIdentifier;

    public String generateJwt(Authentication authentication, boolean rememberMe) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        LocalDateTime expirationDate = LocalDateTime.now();
        expirationDate = rememberMe ?
                expirationDate.plusYears(Long.valueOf(jwtExpirationIfRememberedYears)) :
                expirationDate.plusHours(Long.valueOf(jwtExpirationHours));
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(Date.valueOf(LocalDate.now()))
                .setExpiration(Timestamp.valueOf(expirationDate))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(authorization);
        if (hasText(bearer) && bearer.startsWith(tokenIdentifier)) {
            return bearer.substring(tokenIdentifier.length());
        }
        return null;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

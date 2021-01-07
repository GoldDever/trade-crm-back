package ru.javamentor.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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

    private final AuthenticationManager authenticationManager;

    public JwtProvider(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String generateJwt(boolean rememberMe, String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String userNamePrincipal = authentication.getName();
        LocalDateTime expirationDate = LocalDateTime.now();
        expirationDate = rememberMe ?
                expirationDate.plusYears(Long.valueOf(jwtExpirationIfRememberedYears)) :
                expirationDate.plusHours(Long.valueOf(jwtExpirationHours));
        return Jwts.builder()
                .setSubject(userNamePrincipal)
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
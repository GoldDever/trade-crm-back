package ru.javamentor.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import static org.springframework.util.StringUtils.hasText;

//@PropertySource("classpath : application.properties")
@Component
public class JwtProvider {

//    @Value("$(jwt.secret)")
    private String jwtSecret = "javaJWt";

//    @Value("${jwt.expirationHours)")
    private String jwtExpirationHours = "1";

//    @Value("${jwt.expirationIfRememberedYears)")
    private String jwtExpirationIfRememberedYears = "1";

//    @Value("$(jwt.authorization")
    private String authorization = "Authorization";

//    @Value("$(jwt.tokenIdentifier")
    private String tokenIdentifier = "Bearer ";

    public String generateJwt(boolean rememberMe, String username, String password) {
        Authentication authentication = authenticationJwt(username, password);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(Date.valueOf(LocalDate.now()))
                .setExpiration(rememberMe 
                            ? Date.from(Instant.from(ZonedDateTime.now().plusYears(Long.valueOf(jwtExpirationIfRememberedYears))))
                            : Date.from(Instant.from(ZonedDateTime.now().plusHours(Long.valueOf(jwtExpirationHours)))))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Authentication authenticationJwt(String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);//authenticationManager.authenticate(
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
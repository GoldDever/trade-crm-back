package ru.javamentor.configuration.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.javamentor.security.JwtAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import static org.springframework.util.StringUtils.hasText;

@PropertySource("classpath:values.properties")
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;// = "javaJWt";

    @Value("${jwt.expirationHours}")
    private long jwtExpirationHours;//= 1;

    @Value("${jwt.expirationIfRememberedYears}")
    private long jwtExpirationIfRememberedYears;//= "1";

    @Value("${jwt.authorization}")
    private String authorization;// = "Authorization";

    @Value("${jwt.tokenIdentifier}")
    private String tokenIdentifier;// = "Bearer ";

    public String generateJwt(boolean rememberMe, String username, String password) {
        Authentication authentication = authenticationJwt(username, password);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(Date.valueOf(LocalDate.now()))
                .setExpiration(rememberMe
                        ? Date.from(Instant.from(ZonedDateTime.now().plusYears(jwtExpirationIfRememberedYears)))
                        : Date.from(Instant.from(ZonedDateTime.now().plusHours(jwtExpirationHours))))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(Date.valueOf(LocalDate.now()));
        } catch (ExpiredJwtException e) {
            //TODO добавить логер и выводить об ошибке в логере
            return false;
        } catch (UnsupportedJwtException e) {
            throw new JwtAuthenticationException("UnsupportedJwt" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new JwtAuthenticationException("UncorrectJwt" + e.getMessage());
        } catch (SignatureException e) {
            throw new JwtAuthenticationException("Signature problem" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JwtAuthenticationException("Illegal Argument Exception" + e.getMessage());
        }
    }


        public Authentication authenticationJwt(String username, String password) {
            return new UsernamePasswordAuthenticationToken(username, password);
        }

        public String getTokenFromRequest(HttpServletRequest request) {
            String bearer = request.getHeader(authorization);
            if (hasText(bearer) && bearer.startsWith(tokenIdentifier)) {
                return bearer.substring(tokenIdentifier.length());
            }
            return null;
        }

        public String getLoginFromToken(String token) {
            try {
                return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
            } catch (JwtException e) {
                throw new JwtAuthenticationException("Some problem, with token" + e.getMessage());
            }
        }
    }
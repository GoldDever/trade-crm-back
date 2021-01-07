package ru.javamentor.configuration.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.javamentor.service.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl jwtUserDetailsService;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtProvider.getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            UserDetails jwtUserDetails = jwtUserDetailsService.loadUserByUsername(userLogin);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(jwtUserDetails, "", jwtUserDetails.getAuthorities()));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

package ru.javamentor.configuration.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@ComponentScan("ru.javamentor.service")
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    private final UserDetailsService jwtUserDetailsService;

    public JwtFilter(JwtProvider jwtProvider, @Qualifier("userDetailsServiceImpl") UserDetailsService jwtUserDetailsService) {
        this.jwtProvider = jwtProvider;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtProvider.getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            UserDetails jwtUserDetails = jwtUserDetailsService.loadUserByUsername(jwtProvider.getLoginFromToken(token));
            if (jwtUserDetails != null) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(jwtUserDetails, "", jwtUserDetails.getAuthorities()));
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}


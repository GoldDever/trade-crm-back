package ru.javamentor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.javamentor.configuration.JwtConfigurer;
import ru.javamentor.configuration.jwt.JwtFilter;
import ru.javamentor.configuration.jwt.JwtProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "ru.javamentor.configuration")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    private final UserDetailsService userDetailsService;

    private final JwtProvider jwtProvider;

    private static final String LOGIN_ENDPOINT = "api/auth/login";


    @Autowired
    public SecurityConfig(JwtFilter jwtFilter, UserDetailsService userDetailsService, JwtProvider jwtProvider) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()//
                .csrf().disable() //временно
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("").permitAll()
                .antMatchers("/api/admin/**").hasRole("ROLE_ADMIN")
                .antMatchers("/register", LOGIN_ENDPOINT).permitAll()
                .and()
                .apply(new JwtConfigurer(jwtProvider))
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

}

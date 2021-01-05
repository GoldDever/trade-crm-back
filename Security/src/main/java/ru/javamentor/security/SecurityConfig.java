package ru.javamentor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

@Configuration //позволяет нам использовать @Bean, проксируются через CGLIB
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    /*

      @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //        auth.userDetailsService(userDetailsService);
            auth.inMemoryAuthentication().withUser("ADMIN").password("ADMIN").roles("ADMIN");
        }
    */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");
            filter.setForceEncoding(true);
            http.addFilterBefore(filter, CsrfFilter.class);*/



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()//
                .csrf().disable() //временно
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/register", LOGIN_ENDPOINT).permitAll()
                .and()
                .apply(new JwtConfigurer(jwtProvider))
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //http.userDetailsService(userDetailsService);

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

}

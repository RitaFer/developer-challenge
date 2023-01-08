package com.diazero.developerchallenge.config.security;

import com.diazero.developerchallenge.exception.GeneralException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            http
                    .httpBasic()
                    .and()
                    .authorizeHttpRequests()
                    .anyRequest().authenticated().and()
                    .csrf().disable();
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        try {
            auth.inMemoryAuthentication()
                    .withUser("admin")
                    .password(passwordEncoder().encode("testediazero"))
                    .roles("ADMIN", "USER");
            auth.inMemoryAuthentication()
                    .withUser("rita")
                    .password(passwordEncoder().encode("testediazero"))
                    .roles("USER");
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
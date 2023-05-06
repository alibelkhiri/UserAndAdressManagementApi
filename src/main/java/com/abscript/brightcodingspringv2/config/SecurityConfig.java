package com.abscript.brightcodingspringv2.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

   @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
       return http
        .csrf(csrf->csrf.disable())
        .authorizeRequests(auth->{
            auth.antMatchers("/users").permitAll();
            auth.antMatchers("/user").hasRole("USER");
            auth.antMatchers("/admin").hasRole("ADMIN");
        })
        .httpBasic(Customizer.withDefaults())
        .build();
        
    }
    
}

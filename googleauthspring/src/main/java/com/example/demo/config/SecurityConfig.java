package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (necessary for H2 console)
            .csrf(csrf -> csrf.disable())

            // Disable frame options to allow H2 console frames
            .headers(headers -> headers.frameOptions().disable())

            // Configure authorization requests
            .authorizeHttpRequests(auth -> auth
                // Allow access to H2 console
                .requestMatchers("/api/h2-console/").permitAll()

                // Allow access to other static resources and open routes
                .requestMatchers(
                    "/", "/home", "/api/", 
                    "/static/", "/public/", "/resources/", "/webjars/", 
                    "/**"
                ).permitAll()

                // Require authentication for any other requests
                .anyRequest().authenticated()
            );

        return http.build();
    }
    
}

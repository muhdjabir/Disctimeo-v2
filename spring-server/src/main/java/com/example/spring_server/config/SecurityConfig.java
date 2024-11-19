package com.example.spring_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
         * This is where we configure the security required for our endpoints and setup
         * our app to serve as
         * an OAuth2 Resource Server, using JWT validation.
         */
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        // Permit all GET requests
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()

                        // Authenticate all other types of requests (POST, PUT, DELETE, etc.)
                        .anyRequest().authenticated())
                .cors(withDefaults()) // Enable CORS if needed
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())) // Set up OAuth2 Resource Server with JWT
                                                                            // validation
                .build();
    }
}

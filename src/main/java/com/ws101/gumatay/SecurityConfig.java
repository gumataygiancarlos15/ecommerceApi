package com.ws101.gumatay;

import com.ws101.gumatay.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        // Protected endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/products/**").authenticated()
                        // Admin only
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/v1/**"))

                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED)
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return builder.build();
    }
}
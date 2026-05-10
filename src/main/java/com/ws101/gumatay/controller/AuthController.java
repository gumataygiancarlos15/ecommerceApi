package com.ws101.gumatay.controller;

import com.ws101.gumatay.dto.RegisterRequest;
import com.ws101.gumatay.model.User;
import com.ws101.gumatay.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register endpoint — publicly accessible.
     * Accepts username, password, role.
     * Hashes the password before saving.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @Valid @RequestBody RegisterRequest request) {

        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Username already taken"));
        }

        // Hash password and save user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRole());

        userRepository.save(user);

        return new ResponseEntity<>(
                Map.of("message", "User registered successfully"),
                HttpStatus.CREATED);
    }
}
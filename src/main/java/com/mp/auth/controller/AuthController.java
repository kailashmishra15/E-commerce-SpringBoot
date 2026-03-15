package com.mp.auth.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp.auth.config.JwtService;
import com.mp.auth.dto.AuthResponse;
import com.mp.auth.dto.LoginRequest;
import com.mp.auth.dto.RegisterRequest;
import com.mp.auth.model.User;
import com.mp.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(com.mp.auth.model.Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .build());
    }
}
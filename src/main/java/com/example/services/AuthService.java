package com.example.demo.services;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtTokenProvider jwtTokenProvider,
                       PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(AuthRequest req) {
        return new AuthResponse(jwtTokenProvider.generateToken(req.getEmail()));
    }
}

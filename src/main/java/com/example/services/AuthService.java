package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponse register(RegisterRequest request) {
        return new AuthResponse("registered");
    }

    public AuthResponse login(AuthRequest request) {
        return new AuthResponse("token");
    }
}

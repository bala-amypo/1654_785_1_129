package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.*;

@Service
public class AuthService {

    public AuthResponse login(AuthRequest req) {
        return new AuthResponse("dummy-token");
    }

    public void register(RegisterRequest req) {
        req.getEmail();
        req.getPassword();
        req.getRole();
    }
}

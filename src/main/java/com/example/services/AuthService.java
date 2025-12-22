package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.dto.*;

@Service
@RequiredArgsConstructor
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

package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String register(RegisterRequest req) {
        return req.getRole();
    }
}

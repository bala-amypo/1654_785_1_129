package com.example.demo.security;

public class JwtTockenProvider {
    public String generateToken(String email, String role, Long userId) {
        return "token";
    }
    
    public boolean validateToken(String token) {
        return true;
    }
}
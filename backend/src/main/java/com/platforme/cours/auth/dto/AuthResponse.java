package com.platforme.cours.auth.dto;

public record AuthResponse(String token, String email, String nom, String role) {
}

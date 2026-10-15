package com.platforme.cours.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank String nom,
                              @NotBlank @Email String email,
                              @NotBlank @Size(min = 8, message = "Le mot de passe doit faire au moins 8 caractères") String motDePasse
) {
}

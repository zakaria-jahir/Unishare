package com.platforme.cours.auth.service;

import com.platforme.cours.auth.dto.AuthResponse;
import com.platforme.cours.auth.dto.LoginRequest;
import com.platforme.cours.auth.dto.RegisterRequest;
import com.platforme.cours.auth.model.Role;
import com.platforme.cours.auth.model.Utilisateur;
import com.platforme.cours.auth.repository.UtilisateurRepository;
import com.platforme.cours.auth.security.JwtService;
import com.platforme.cours.auth.security.UtilisateurPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (utilisateurRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cet email est déjà utilisé");
        }

        Utilisateur utilisateur = Utilisateur.builder()
                .nom(request.nom())
                .email(request.email())
                .motDePasse(passwordEncoder.encode(request.motDePasse()))
                .role(Role.ETUDIANT)
                .build();

        utilisateurRepository.save(utilisateur);
        return buildResponse(utilisateur);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.motDePasse()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou mot de passe incorrect");
        }

        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou mot de passe incorrect"));
        return buildResponse(utilisateur);
    }

    private AuthResponse buildResponse(Utilisateur utilisateur) {
        String token = jwtService.generateToken(new UtilisateurPrincipal(utilisateur));
        return new AuthResponse(token, utilisateur.getEmail(), utilisateur.getNom(), utilisateur.getRole().name());
    }
}

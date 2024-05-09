package org.example.api.controller;

import jakarta.validation.Valid;
import org.example.api.dto.LoginRequest;
import org.example.api.dto.LoginResponse;
import org.example.config.SecurityConfigProperties;
import org.example.security.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SecurityConfigProperties securityConfig;

    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService, SecurityConfigProperties securityConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.securityConfig = securityConfig;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Failed to authenticate");
        }

        String jwtToken = jwtService.generate(authentication.getName(), securityConfig.getTtl());
        Date expirationDate = jwtService.extractExpirationDate(jwtToken);
        return new LoginResponse(System.currentTimeMillis(), jwtToken, expirationDate);
    }
}

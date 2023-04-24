package com.backend.api.gateway.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.api.gateway.auth.payload.JWTAuthResponse;
import com.backend.api.gateway.auth.payload.LoginDto;
import com.backend.api.gateway.auth.payload.RegisterDto;
import com.backend.api.gateway.auth.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authService.register(registerDto));
    }
}

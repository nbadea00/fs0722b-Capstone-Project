package com.backend.api.gateway.auth.service;

import com.backend.api.gateway.auth.payload.JWTAuthResponse;
import com.backend.api.gateway.auth.payload.LoginDto;
import com.backend.api.gateway.auth.payload.RegisterDto;

public interface AuthService {
    
	JWTAuthResponse login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}

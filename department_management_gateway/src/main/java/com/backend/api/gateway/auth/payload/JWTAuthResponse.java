package com.backend.api.gateway.auth.payload;

import java.util.Set;

import com.backend.api.gateway.auth.entity.ERole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
	private String username;
    private String accessToken;
    private Set<ERole> rolesName;
    private String tokenType = "Bearer";
}

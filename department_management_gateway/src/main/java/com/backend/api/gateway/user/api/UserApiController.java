package com.backend.api.gateway.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.api.gateway.auth.entity.User;
import com.backend.api.gateway.auth.repository.UserRepository;
import com.backend.api.gateway.auth.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
	
	@Autowired UserApiConfig userApiConfig;
	@Autowired JwtTokenProvider jwtTokenProvider;
	@Autowired UserRepository userRepository;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> postNewUser(@RequestBody Object user,
			@RequestHeader(value = "Authorization") String token){
		String param = jwtTokenProvider.getUsername(token.substring(7));
		User u = userRepository.findByUsernameOrEmail(param, param).get();
		return ResponseEntity.ok(userApiConfig.getRestTemplate().postForObject("/users?id=" + u.getId(), user, Object.class));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUsers(@RequestBody Object user,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(userApiConfig.getRestTemplate().exchange("/users?page=" + page + "&dim=" + dim, HttpMethod.GET, null, Object.class).getBody());
	}
}

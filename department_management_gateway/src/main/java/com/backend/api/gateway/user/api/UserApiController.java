package com.backend.api.gateway.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		return ResponseEntity.ok(userApiConfig.getRestTemplate().postForObject("/users?id=" + jwtTokenProvider.getId(token.substring(7)), user, Object.class));
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> putUser(@RequestBody Object user, 
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(userApiConfig.getRestTemplate().exchange("/users?id=" + jwtTokenProvider.getId(token.substring(7)), HttpMethod.PUT, new HttpEntity<Object>(user), Object.class).getBody());
	}
	
	@PostMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postNewUserAdmin(@RequestBody Object user,
			@PathVariable String id){
		return ResponseEntity.ok(userApiConfig.getRestTemplate().postForObject("/users/" + id, user, Object.class));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putUserAdmin(@RequestBody Object user, 
			@PathVariable String id){
		return ResponseEntity.ok(userApiConfig.getRestTemplate().exchange("/users/" + id, HttpMethod.PUT, new HttpEntity<Object>(user), Object.class).getBody());
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> getAllUsers(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(userApiConfig.getRestTemplate().exchange("/users?page=" + page + "&dim=" + dim, HttpMethod.GET, null, Object.class).getBody());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(userApiConfig.getRestTemplate().exchange("/users/" + id, HttpMethod.GET, null, Object.class).getBody());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delUser(@PathVariable Long id){
		return ResponseEntity.ok(userApiConfig.getRestTemplate().exchange("/users/" + id, HttpMethod.DELETE, null, String.class).getBody());
	}
	
	
}

package com.backend.api.gateway.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.backend.api.gateway.auth.entity.User;
import com.backend.api.gateway.auth.payload.RegisterDto;
import com.backend.api.gateway.auth.repository.UserRepository;
import com.backend.api.gateway.auth.service.AuthService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class RegisterTest {

	@Autowired AuthService authService;
	@Autowired UserRepository repo;
	
	@Test
	@Order(1)
	public void createNewUserTest() {
		RegisterDto newUser = RegisterDto.builder()
				.email("test@test.test")
				.username("test")
				.password("#Test00#")
				.roles(Set.of("ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR"))
				.build();
		authService.register(newUser);
		
		assertTrue(repo.findByUsername(newUser.getUsername()).isPresent());
	}
	
	@Test
	@Order(2)
	public void findByUsernameUserTest() {
		assertTrue(repo.findByUsername("test").isPresent());
	}
	
	@Test
	@Order(3)
	public void updateUserTest() {
		User u = repo.findByUsername("test").orElseThrow();
		u.setUsername("testNuovo");
		repo.save(u);
		assertTrue(repo.findByUsername("testNuovo").isPresent());
	}
	
	@Test
	@Order(4)
	public void delUserTest() {
		repo.delete(repo.findByUsername("testNuovo").get());
		assertTrue(repo.findByUsername("testNuovo").isEmpty());
	}
}

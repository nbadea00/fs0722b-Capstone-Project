package com.backend.api.gateway.auth;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.backend.api.gateway.auth.payload.LoginDto;
import com.backend.api.gateway.auth.service.AuthService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class LoginTest {

	@Autowired AuthService authService;
	
	@Test
	@Order(1)
	public void loginUsernameTest() {
		LoginDto login = LoginDto.builder()
				.username("admin")
				.password("#Admin00#")
				.build();
		
		String token = authService.login(login);
		
		assertTrue(token != null);
	}
	
	@Test
	@Order(2)
	public void loginEmailTest() {
		LoginDto login = LoginDto.builder()
				.username("admin@admin.admin")
				.password("#Admin00#")
				.build();
		
		String token = authService.login(login);
		
		assertTrue(token != null);
	}
}

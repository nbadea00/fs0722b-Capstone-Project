package com.backend.api.gateway.webclient;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean(name = "webClientEmployeeApi")
	WebClient webClientEmployee() {
		return WebClient.builder()
				.baseUrl("http://localhost:8081/api/employees")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081/api/employees"))
				.build();
	}
	
	@Bean(name = "webClientTeamsApi")
	WebClient webClientTeam() {
		return WebClient.builder()
				.baseUrl("http://localhost:8082/api/teams")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8082/api/teams"))
				.build();
	}
	
	@Bean(name = "webClientSectionsApi")
	WebClient webClientSection() {
		return WebClient.builder()
				.baseUrl("http://localhost:8082/api/sections")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8082/api/sections"))
				.build();
	}
	
	@Bean(name = "webClientDepartmentsApi")
	WebClient webClientDepartment() {
		return WebClient.builder()
				.baseUrl("http://localhost:8082/api/departments")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8082/api/departments"))
				.build();
	}
	
	@Bean(name = "webClientProjectApi")
	WebClient webClientProject() {
		return WebClient.builder()
				.baseUrl("http://localhost:8083/api/projects")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8083/api/projects"))
				.build();
	}
	
	@Bean(name = "webClientTaskApi")
	WebClient webClientTask() {
		return WebClient.builder()
				.baseUrl("http://localhost:8083/api/tasks")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8083/api/task"))
				.build();
	}
	
}

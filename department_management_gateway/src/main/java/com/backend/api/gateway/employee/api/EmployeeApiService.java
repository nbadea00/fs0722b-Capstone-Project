package com.backend.api.gateway.employee.api;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import com.backend.api.gateway.webclient.WebClientService;

import reactor.core.publisher.Mono;

@Service
public class EmployeeApiService {
	
	@Autowired WebClient webClientEmployeeApi;
	@Autowired WebClientService webClientService;
	
	public Boolean getAuth(Long userId, String auth) {
		return webClientService
				.errorHandleResponse(
						webClientEmployeeApi
						.get()
						.uri("/auth?id=" + userId + "&auth=" + auth)
						.retrieve())
				.bodyToMono(Boolean.class)
				.block();
	}
	
	public EmployeeDto createNewEmployee(EmployeeDto employee, Long id) {
		return webClientService
				.errorHandleResponse(
						webClientEmployeeApi
						.post()
						.uri("?id=" + id)
						.body(Mono.just(employee),EmployeeDto.class)
						.retrieve())
				.bodyToMono(EmployeeDto.class)
				.block();
	}
	
	public EmployeeDto modifyEmployee(EmployeeDto employee, Long id) {
		return webClientService
				.errorHandleResponse(
						webClientEmployeeApi
						.put()
						.uri("?id=" + id)
						.body(Mono.just(employee),EmployeeDto.class)
						.retrieve())
				.bodyToMono(EmployeeDto.class)
				.block();
	}
	
	public List<EmployeeDto> getAllEmployee(int page, int dim) {
		return webClientService
				.errorHandleResponse(
						webClientEmployeeApi
						.get()
						.uri("?page=" + page + "&dim=" + dim)
						.retrieve())
				.bodyToMono(new ParameterizedTypeReference<List<EmployeeDto>>() {})
				.block();
	}
	
	public List<EmployeeDto> getEmployeesBySetIdCredentials(int page, int dim, Set<Long> idCredentials) {
		return webClientService
				.errorHandleResponse(
						((RequestBodySpec) webClientEmployeeApi
						.post()
						.uri("/findBySetIdCredentials?page=" + page + "&dim=" + dim))
						.body(Mono.just(idCredentials), new ParameterizedTypeReference<Set<Long>>() {})
						.retrieve())
				.bodyToMono(new ParameterizedTypeReference<List<EmployeeDto>>() {})
				.block();
	}
	
	public EmployeeDto getEmployeeById(Long id) {
		return webClientService
				.errorHandleResponse(
						webClientEmployeeApi
						.get()
						.uri("/" + id)
						.retrieve())
				.bodyToMono(EmployeeDto.class)
				.block();
	}
	
	public String deleteEmployeeById(Long id) {
		return webClientService
				.errorHandleResponse(
						webClientEmployeeApi.delete()
						.uri("/" + id)
						.retrieve())
				.bodyToMono(String.class)
				.block();		
	}
}

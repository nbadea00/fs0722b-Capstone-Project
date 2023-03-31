package com.backend.api.gateway.employee.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
	
	public Page<EmployeeDto> getAllEmployee(int page, int dim) {
		return webClientService
				.errorHandleResponse(
						webClientEmployeeApi
						.get()
						.uri("?page=" + page + "&dim=" + dim)
						.retrieve())
				.bodyToMono(new ParameterizedTypeReference<Page<EmployeeDto>>() {})
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

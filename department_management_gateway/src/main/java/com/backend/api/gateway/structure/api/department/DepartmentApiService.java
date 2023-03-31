package com.backend.api.gateway.structure.api.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.backend.api.gateway.auth.exception.MyAPIException;
import com.backend.api.gateway.employee.api.EmployeeApiService;
import com.backend.api.gateway.webclient.WebClientService;

import reactor.core.publisher.Mono;

@Service
public class DepartmentApiService {

	@Autowired WebClient webClientDepartmentsApi;
	@Autowired WebClientService webClientService;
	@Autowired EmployeeApiService employeeApiService;
	
	public DepartmentDto createNewDepartment(DepartmentDto department) {
		if(employeeApiService.getAuth(
				department.getDepartmentHeadId(),
				"DEPARTEMENT_HEAD")) throw new MyAPIException(HttpStatus.UNAUTHORIZED, "not authorized");
		return webClientService
				.errorHandleResponse(
					webClientDepartmentsApi
					.post()
					.body(Mono.just(department),DepartmentDto.class)
					.retrieve())
				.bodyToMono(DepartmentDto.class)
				.block();
	}
	
	public DepartmentDto modifyDepartmentByAdmin(Long idDepartment, DepartmentDto department, Long id) {
		return webClientService
				.errorHandleResponse(
					webClientDepartmentsApi
					.put()
					.uri("/" + idDepartment + "?idUser=" + id)
					.body(Mono.just(department),DepartmentDto.class)
					.retrieve())
				.bodyToMono(DepartmentDto.class)
				.block();
	}
	
	public Page<DepartmentDto> getAllDepartment(int page, int dim) {
		return webClientService
				.errorHandleResponse(
						webClientDepartmentsApi.get()
						.uri("?page=" + page + "&dim=" + dim)
						.retrieve())
				.bodyToMono(new ParameterizedTypeReference<Page<DepartmentDto>>() {})
				.block();		
	}
	
	public DepartmentDto getDepartmentById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientDepartmentsApi.get()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(DepartmentDto.class)
				.block();		
	}
	
	public String deleteDepartmentById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientDepartmentsApi.delete()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(String.class)
				.block();		
	}
	
	public Boolean getAuth(Long id, DepartmentDto department) {
		return webClientService
				.errorHandleResponse(
						webClientDepartmentsApi
						.post()
						.uri("/auth?id=" + id)
						.body(Mono.just(department),DepartmentDto.class)
						.retrieve())
				.bodyToMono(Boolean.class)
				.block();
	}
	
}

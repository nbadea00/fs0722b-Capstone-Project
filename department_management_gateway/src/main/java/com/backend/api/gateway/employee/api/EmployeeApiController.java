package com.backend.api.gateway.employee.api;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.backend.api.gateway.auth.security.JwtTokenProvider;


@RestController
@RequestMapping("/api/employees")
public class EmployeeApiController {
	
	@Autowired JwtTokenProvider jwtTokenProvider;
	@Autowired EmployeeApiService employeeApiService;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<EmployeeDto> postNewEmployee(
			@RequestBody EmployeeDto employee,
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				employeeApiService
				.createNewEmployee(employee, Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<EmployeeDto> putEmployee(
			@RequestBody EmployeeDto employee, 
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				employeeApiService
				.modifyEmployee(employee, Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<EmployeeDto> putEmployeeAdmin(
			@RequestBody EmployeeDto employee, 
			@PathVariable Long id){
		return ResponseEntity.ok(
				employeeApiService
				.modifyEmployee(employee, id));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(
				employeeApiService
				.getAllEmployee(page, dim));
	}
	
	@PostMapping("/findBySetIdCredentials")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeeSet(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim,
			@RequestBody Set<Long> idCredentials){
		return ResponseEntity.ok(
				employeeApiService
				.getEmployeesBySetIdCredentials(page, dim, idCredentials));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
		return ResponseEntity.ok(
				employeeApiService
				.getEmployeeById(id));
	}
	
	@GetMapping("/token")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<EmployeeDto> getEmployeeByToken(@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				employeeApiService
				.getEmployeeById(Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delEmployee(@PathVariable Long id){
		return ResponseEntity.ok(
				employeeApiService
				.deleteEmployeeById(id));
	}
	
	
}

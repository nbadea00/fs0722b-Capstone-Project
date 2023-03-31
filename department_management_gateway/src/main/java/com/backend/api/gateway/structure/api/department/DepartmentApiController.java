package com.backend.api.gateway.structure.api.department;

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
@RequestMapping("/api/departments")
public class DepartmentApiController {
	
	@Autowired DepartmentApiService departmentApiService;
	@Autowired JwtTokenProvider jwtTokenProvider;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<DepartmentDto> postDepartment(@RequestBody DepartmentDto department){
		return ResponseEntity.ok(
				departmentApiService
				.createNewDepartment(department));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<DepartmentDto> putDepartment(
			@RequestBody DepartmentDto department, 
			@PathVariable Long id,
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				departmentApiService
				.modifyDepartmentByAdmin(id, department, Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Page<DepartmentDto>> getAllDepartments(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(
				departmentApiService
				.getAllDepartment(page, dim));
				
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id){
		return ResponseEntity.ok(
				departmentApiService
				.getDepartmentById(id));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delDepartment(@PathVariable Long id){
		return ResponseEntity.ok(
				departmentApiService
				.deleteDepartmentById(id));
	}
}

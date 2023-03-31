package com.backend.api.gateway.project.api.project;

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
@RequestMapping("/api/projects")
public class ProjectApiController {
	@Autowired ProjectApiService projectApiService;
	@Autowired JwtTokenProvider jwtTokenProvider;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<ProjectDto> postProject(
			@RequestBody ProjectDto project,
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				projectApiService
				.createNewProject(project, Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<ProjectDto> putProject(
			@RequestBody ProjectDto project, 
			@PathVariable Long id,
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				projectApiService
				.modifyProjectByAdmin(id, project, Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Page<ProjectDto>> getAllProjects(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(
				projectApiService
				.getAllProject(page, dim));
				
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id){
		return ResponseEntity.ok(
				projectApiService
				.getProjectById(id));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delProject(@PathVariable Long id){
		return ResponseEntity.ok(
				projectApiService
				.deleteProjectById(id));
	}
}

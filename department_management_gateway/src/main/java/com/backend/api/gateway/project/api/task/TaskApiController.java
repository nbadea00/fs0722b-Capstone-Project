package com.backend.api.gateway.project.api.task;

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
@RequestMapping("/api/tasks")
public class TaskApiController {
	@Autowired TaskApiService taskApiService;
	@Autowired JwtTokenProvider jwtTokenProvider;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<TaskDto> postTask(
			@RequestBody TaskDto task,
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				taskApiService
				.createNewTask(task, Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<TaskDto> putTask(
			@RequestBody TaskDto task, 
			@PathVariable Long id,
			@RequestHeader(value = "Authorization") String token){
		return ResponseEntity.ok(
				taskApiService
				.modifyTaskByAdmin(id, task, Long.parseLong(jwtTokenProvider.getId(token.substring(7)))));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Page<TaskDto>> getAllTasks(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(
				taskApiService
				.getAllTask(page, dim));
				
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id){
		return ResponseEntity.ok(
				taskApiService
				.getTaskById(id));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delTask(@PathVariable Long id){
		return ResponseEntity.ok(
				taskApiService
				.deleteTaskById(id));
	}
}

package com.backend.api.project.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/api/tasks")
public class TaskController {
@Autowired TaskService taskService;
	
	@GetMapping
	public ResponseEntity<Page<Task>> getAllUser(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(taskService.findAll(page, dim));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(taskService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delUserById(@PathVariable Long id){
		return ResponseEntity.ok(taskService.delById(id));
	}
	
	@PutMapping("/{idTask}")
	public ResponseEntity<Task> putUser(
			@RequestBody Task task,
			@RequestParam(name = "id") Long idUser,
			@PathVariable Long idTask){
		return ResponseEntity.ok(taskService.update(task, idUser));
	}
	
	@PostMapping
	public ResponseEntity<Task> postNewUser(
			@RequestBody Task task,
			@RequestParam(name = "id") Long id){
		return ResponseEntity.ok(taskService.save(task, id));
	}
}

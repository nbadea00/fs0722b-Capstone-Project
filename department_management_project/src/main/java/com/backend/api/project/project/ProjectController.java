package com.backend.api.project.project;

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
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired ProjectService projectService;
	
	@GetMapping
	public ResponseEntity<Page<Project>> getAllUser(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(projectService.findAll(page, dim));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(projectService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delUserById(@PathVariable Long id){
		return ResponseEntity.ok(projectService.delById(id));
	}
	
	@PutMapping("/{idProject}")
	public ResponseEntity<Project> putUser(
			@RequestBody Project project,
			@RequestParam(name = "id") Long idUser,
			@PathVariable Long idProject){
		return ResponseEntity.ok(projectService.update(project, idUser));
	}
	
	@PostMapping
	public ResponseEntity<Project> postNewUser(
			@RequestBody Project project,
			@RequestParam(name = "id") Long id){
		return ResponseEntity.ok(projectService.save(project, id));
	}
}

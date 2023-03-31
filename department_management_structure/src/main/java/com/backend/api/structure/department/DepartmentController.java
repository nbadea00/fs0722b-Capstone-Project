package com.backend.api.structure.department;

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
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class DepartmentController {

	@Autowired DepartmentService departmentService;
	
	@GetMapping
	public ResponseEntity<Page<Department>> getAllDepartment(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "0") int dim){
		return ResponseEntity.ok(departmentService.findAll(page, dim));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id){
		return ResponseEntity.ok(departmentService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Department> postDepartment(@RequestBody Department department){
		return ResponseEntity.ok(departmentService.save(department));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Department> putDepartment(
			@RequestBody Department department, 
			@PathVariable Long id,
			@RequestParam(name = "idUser") Long idUser){
		return ResponseEntity.ok(departmentService.update(department, id, idUser));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delDepartment(@PathVariable Long id){
		return ResponseEntity.ok(departmentService.deleteById(id));
	}
}

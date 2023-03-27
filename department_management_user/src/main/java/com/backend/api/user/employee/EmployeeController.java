package com.backend.api.user.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class EmployeeController {

	@Autowired EmployeeService es;
	
	@GetMapping
	public ResponseEntity<Page<Employee>> getAllUser(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(es.findAll(page, dim));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(es.findById(id));
	}
	
	@DeleteMapping
	public ResponseEntity<String> delUserById(@PathVariable Long id){
		return ResponseEntity.ok(es.delById(id));
	}
	
	@PostMapping
	public ResponseEntity<Employee> postNewUser(@RequestBody Employee employee,
			@RequestParam(name = "id") Long id){
		return ResponseEntity.ok(es.save(employee, id));
	}
}

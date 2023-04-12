package com.backend.api.structure.section;

import java.util.List;

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
@RequestMapping("/api/sections")
public class SectionController {

	@Autowired SectionService sectionService;
	
	@GetMapping
	public ResponseEntity<List<Section>> getAllSection(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(sectionService.findAll(page, dim));
	}
	
	@GetMapping("/findByDepartmentId")
	public ResponseEntity<List<Section>> getAllSectionByDepartmentId(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim,
			@RequestParam(name = "departmentId") Long departmentId){
		return ResponseEntity.ok(sectionService.findByDepartmentId(departmentId, page, dim));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Section> getSectionById(@PathVariable Long id){
		return ResponseEntity.ok(sectionService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Section> postSection(
			@RequestBody Section section,
			@RequestParam(name = "idUser") Long idUser){
		return ResponseEntity.ok(sectionService.save(section, idUser));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Section> putSection(
			@RequestBody Section section, 
			@PathVariable Long id,
			@RequestParam(name = "idUser") Long idUser){
		return ResponseEntity.ok(sectionService.update(section, id, idUser));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delSection(@PathVariable Long id){
		return ResponseEntity.ok(sectionService.deleteById(id));
	}
	
	@PostMapping("/auth")
	public ResponseEntity<Boolean> getAuth(
			@RequestParam(name = "id") Long id,
			@RequestBody Section section){
		return ResponseEntity.ok(sectionService.getAuth(id, section));
	}
}

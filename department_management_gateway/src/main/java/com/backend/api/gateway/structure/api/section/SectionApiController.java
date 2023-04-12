package com.backend.api.gateway.structure.api.section;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sections")
public class SectionApiController {

	@Autowired SectionApiService sectionApiService;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<SectionDto> postSection(@RequestBody SectionDto section){
		return ResponseEntity.ok(
				sectionApiService
				.createNewSection(section));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<SectionDto> putSection(
			@RequestBody SectionDto section, 
			@PathVariable Long id){
		return ResponseEntity.ok(
				sectionApiService
				.modifySectionByAdmin(id, section));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<SectionDto>> getAllSectiona(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(
				sectionApiService
				.getAllSection(page, dim));
				
	}
	
	@GetMapping("/findByDepartmentId")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<SectionDto>> getAllSectiona(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim,
			@RequestParam(name = "departmentId") Long departmentId){
		return ResponseEntity.ok(
				sectionApiService
				.getAllSectionByDepartmentId(page, dim, departmentId));
				
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<SectionDto> getSectionById(@PathVariable Long id){
		return ResponseEntity.ok(
				sectionApiService
				.getSectionById(id));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<String> delSection(@PathVariable Long id){
		return ResponseEntity.ok(
				sectionApiService
				.deleteSectionById(id));
	}
}

package com.backend.api.structure.section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SectionController {

	@Autowired SectionService sectionService;
	
	@GetMapping
	public ResponseEntity<Page<Section>> getAllSection(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "0") int dim){
		return ResponseEntity.ok(sectionService.findAll(page, dim));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Section> getSectionById(@PathVariable Long id){
		return ResponseEntity.ok(sectionService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Section> postSection(
			@RequestBody Section section,
			@RequestParam(name = "SectionHeadId") Long sectionManagerId){
		return ResponseEntity.ok(sectionService.save(section, sectionManagerId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Section> putSection(@RequestBody Section section, @PathVariable Long id){
		return ResponseEntity.ok(sectionService.update(section, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delSection(@PathVariable Long id){
		return ResponseEntity.ok(sectionService.deleteById(id));
	}
}

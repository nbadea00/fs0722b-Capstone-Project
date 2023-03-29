package com.backend.api.structure.section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.structure.exception.MyAPIException;
import com.backend.api.structure.exception.ResourceNotFoundException;

@Service
public class SectionService {
	@Autowired SectionRepository repo;
	
	public Section save(Section args, Long sectionManagerId) {
		if(sectionManagerId == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		if(repo.existsByName(args.getName())) throw new MyAPIException(HttpStatus.NOT_FOUND, "Section name already used");
		args.setSectionManagerId(sectionManagerId);
		return repo.save(args);
	}
	
	public Section update(Section args, Long id) {
		if(id == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Section", "id", id.toString()));
		if(repo.existsByNameAndIdNot(args.getName(), id)) throw new MyAPIException(HttpStatus.NOT_FOUND, "Section name already used");
		return repo.save(args);
	}
	
	public Section findById(Long id) {
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Section", "id", id.toString()));
	}
	
	public Section findByName(String name) {
		return repo.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Section", "name", name));
	}
	
	public Page<Section> findByDepartmentId(Long  departmentId, int page, int dim) {
		return repo.findByDepartmentId(PageRequest.of(page, dim),departmentId);
	}
	
	public Section findBySectionManagerId(Long sectionManagerId) {
		return repo.findBySectionManagerId(sectionManagerId).orElseThrow(()-> new ResourceNotFoundException("Section", "sectionManagerId", sectionManagerId.toString()));
	}
	
	public Page<Section> findAll(int page, int dim) {
		return repo.findAll(PageRequest.of(page, dim));
	}
	
	public String deleteById(Long id) {
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Section", "id", id.toString()));
		repo.deleteById(id);
		return "Section remuved";
	}
}

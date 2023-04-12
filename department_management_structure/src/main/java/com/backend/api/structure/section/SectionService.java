package com.backend.api.structure.section;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.structure.department.DepartmentService;
import com.backend.api.structure.exception.MyAPIException;
import com.backend.api.structure.exception.ResourceNotFoundException;

@Service
public class SectionService {
	@Autowired SectionRepository repo;
	@Autowired DepartmentService departmentService;
	
	public Section save(Section args, Long idUser) {
		if(args.getSectionManagerId() == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		
		if(repo.existsByName(args.getName())) throw new MyAPIException(HttpStatus.NOT_FOUND, "Section name already used");
		
		if(!departmentService.getAuth(idUser, args.getDepartment())) throw new MyAPIException(HttpStatus.UNAUTHORIZED, "not authorized");
		
		return repo.save(args);
	}
	
	public Section update(Section args, Long id, Long idUser) {
		if(id == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Section", "id", id.toString()));
		
		if(repo.existsByNameAndIdNot(args.getName(), id)) throw new MyAPIException(HttpStatus.NOT_FOUND, "Section name already used");
		
		if(!getAuth(idUser, args)) throw new MyAPIException(HttpStatus.UNAUTHORIZED, "not authorized");
		
		return repo.save(args);
	}
	
	public Section findById(Long id) {
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Section", "id", id.toString()));
	}
	
	public Section findByName(String name) {
		return repo.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Section", "name", name));
	}
	
	public List<Section> findByDepartmentId(Long  departmentId, int page, int dim) {
		return repo.findByDepartmentId(PageRequest.of(page, dim),departmentId).toList();
	}
	
	public Section findBySectionManagerId(Long sectionManagerId) {
		return repo.findBySectionManagerId(sectionManagerId).orElseThrow(()-> new ResourceNotFoundException("Section", "sectionManagerId", sectionManagerId.toString()));
	}
	
	public List<Section> findAll(int page, int dim) {
		return repo.findAll(PageRequest.of(page, dim)).toList();
	}
	
	public String deleteById(Long id) {
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Section", "id", id.toString()));
		repo.deleteById(id);
		return "Section remuved";
	}
	
	public Boolean getAuth(Long idUser, Section section) {
		return repo.findBySectionManagerId(idUser).equals(repo.findById(section.getId())) || 
				departmentService.getAuth(idUser, section.getDepartment());
	}
}

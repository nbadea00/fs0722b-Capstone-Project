package com.backend.api.structure.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.structure.exception.MyAPIException;
import com.backend.api.structure.exception.ResourceNotFoundException;

@Service
public class DepartmentService {

	@Autowired DepartmentRepository repo;
	
	public Department save(Department args, Long departmentHeadId) {
		if(departmentHeadId == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		if(repo.existsByName(args.getName())) throw new MyAPIException(HttpStatus.NOT_FOUND, "Department name already used");
		args.setDepartmentHeadId(departmentHeadId);
		return repo.save(args);
	}
	
	public Department update(Department args, Long id) {
		if(id == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department", "id", id.toString()));
		if(repo.existsByNameAndIdNot(args.getName(), id)) throw new MyAPIException(HttpStatus.NOT_FOUND, "Department name already used");
		return repo.save(args);
	}
	
	public Department findById(Long id) {
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department", "id", id.toString()));
	}
	
	public Department findByName(String name) {
		return repo.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Department", "name", name));
	}
	
	public Page<Department> findAll(int page, int dim) {
		return repo.findAll(PageRequest.of(page, dim));
	}
	
	public String deleteById(Long id) {
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department", "id", id.toString()));
		repo.deleteById(id);
		return "Department remuved";
	}
}

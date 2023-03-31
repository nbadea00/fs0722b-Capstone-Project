package com.backend.api.project.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.backend.api.project.exception.ResourceNotFoundException;

@Service
public class ProjectService {

	@Autowired ProjectRepository repo;
	
	public Project save(Project project, Long userId) {
		return repo.save(project);
	}
	
	public Project update(Project project, Long userId) {
		return repo.save(project);
	}
	
	public Project findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "id",  id));
	}
	
	public Page<Project> findAll(int page, int dim) {
		return repo.findAll(PageRequest.of(page, dim));
	}
	
	public String delById(Long id) {
		repo.deleteById(id);
		return "Project remuved";
	}
}

package com.backend.api.project.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.backend.api.project.exception.ResourceNotFoundException;


@Service
public class TaskService {
@Autowired TaskRepository repo;
	
	public Task save(Task task, Long userId) {
		return repo.save(task);
	}
	
	public Task update(Task task, Long userId) {
		return repo.save(task);
	}
	
	public Task findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id",  id));
	}
	
	public Page<Task> findAll(int page, int dim) {
		return repo.findAll(PageRequest.of(page, dim));
	}
	
	public String delById(Long id) {
		repo.deleteById(id);
		return "Task remuved";
	}
}

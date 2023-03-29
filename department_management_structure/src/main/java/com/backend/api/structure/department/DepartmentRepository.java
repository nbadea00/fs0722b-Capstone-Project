package com.backend.api.structure.department;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	boolean existsByNameAndIdNot(String name, Long id);
	
	boolean existsByName(String name);
	
	Optional<Department> findByName(String name);
	
	Optional<Department> findByDepartmentHeadId(Long departmentHeadId);
}

package com.backend.api.structure.section;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section, Long> {
	boolean existsByNameAndIdNot(String name, Long id);
	
	boolean existsByName(String name);
	
	Optional<Section> findByName(String name);
	
	Optional<Section> findBySectionManagerId(Long sectionManagerId);
	
	@Query("SELECT s FROM Section s WHERE s.department.id = :departmentId")
	Page<Section> findByDepartmentId(Pageable page,Long departmentId);
}

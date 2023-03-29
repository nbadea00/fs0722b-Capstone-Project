package com.backend.api.structure.section;

import com.backend.api.structure.department.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sections")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(name = "name", nullable = false, unique = true)
	public String name;
	@Column(name = "section_manager_id", nullable = false, unique = true)
	private Long sectionManagerId;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
}

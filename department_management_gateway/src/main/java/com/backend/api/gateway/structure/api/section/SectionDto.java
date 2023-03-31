package com.backend.api.gateway.structure.api.section;

import com.backend.api.gateway.structure.api.department.DepartmentDto;

import lombok.Data;

@Data
public class SectionDto {
	private Long id;
	private String name;
	private Long sectionManagerId;
	private DepartmentDto department;
}

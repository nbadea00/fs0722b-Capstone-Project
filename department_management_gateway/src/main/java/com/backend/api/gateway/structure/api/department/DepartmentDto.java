package com.backend.api.gateway.structure.api.department;

import lombok.Data;

@Data
public class DepartmentDto {
	private Long id;
	private String name;
	private Long departmentHeadId;
}

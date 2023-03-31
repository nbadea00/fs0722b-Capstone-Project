package com.backend.api.gateway.employee.api;

import java.sql.Date;
import java.util.Set;

import lombok.Data;

@Data
public class EmployeeDto {

	private Long id;
	private String firstname;
	private String lastname;
	private String telephone;
	private Long idCredentials;
	private Date birthday;
	private Set<CompanyRoleDto> companyRoles;
	private Set<SkillDto> skills;
}

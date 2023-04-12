package com.backend.api.user.employee;

import java.sql.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.backend.api.user.role.ERole;
import com.backend.api.user.role.Role;
import com.backend.api.user.skill.ESkill;
import com.backend.api.user.skill.Skill;

@Component
public class EmployeeRunner implements ApplicationRunner {

	@Autowired EmployeeRepository employeeRepository;
	@Autowired EmployeeService employeeService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		if(employeeRepository.findAll().size() <= 0) {
			createEmployee(1l);	
			for(Long i = 1l; i < 6l; i++) createEmployee(i+1);	
			for(Long i = 6l; i < 20l; i++) createEmployee(i+1);
		}
	}
	
	public void createEmployee(Long num) {
		Employee e = Employee.builder()
				.firstname("firstname" + num)
				.lastname("lastname" + num)
				.birthday(Date.valueOf("2000-11-" + num))
				.telephone("telefon-number" + num)
				.companyRoles(Set.of(Role.builder().roleName(ERole.EMPLOYEE).build()))
				.skills(Set.of(Skill.builder().skillName(ESkill.JAVA).build(), Skill.builder().skillName(ESkill.JAVASCRIPT).build()))
				.build();
		
		employeeService.save(e, num);
	}
	
	public void createSectionM(Long num) {
		Employee e = Employee.builder()
				.firstname("firstname" + num)
				.lastname("lastname" + num)
				.birthday(Date.valueOf("2000-11-" + num))
				.telephone("telefon-number" + num)
				.companyRoles(Set.of(Role.builder().roleName(ERole.SECTION_MANAGER).build(), Role.builder().roleName(ERole.EMPLOYEE).build()))
				.skills(Set.of(Skill.builder().skillName(ESkill.JAVA).build(), Skill.builder().skillName(ESkill.CSS).build()))
				.build();
		
		employeeService.save(e, num);
	}
	
	public void createDeparHead(Long num) {
		Employee e = Employee.builder()
				.firstname("firstname" + num)
				.lastname("lastname" + num)
				.birthday(Date.valueOf("2000-11-" + num))
				.telephone("telefon-number" + num)
				.companyRoles(Set.of(Role.builder().roleName(ERole.DEPARTEMENT_HEAD).build(), Role.builder().roleName(ERole.EMPLOYEE).build()))
				.skills(Set.of(Skill.builder().skillName(ESkill.JAVA).build(), Skill.builder().skillName(ESkill.ANGULAR).build()))
				.build();
		
		employeeService.save(e, num);
	}

}

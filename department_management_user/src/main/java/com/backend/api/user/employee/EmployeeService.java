package com.backend.api.user.employee;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.user.exception.MyAPIException;
import com.backend.api.user.role.ERole;
import com.backend.api.user.role.Role;
import com.backend.api.user.role.RoleService;
import com.backend.api.user.skill.Skill;
import com.backend.api.user.skill.SkillService;

@Service
public class EmployeeService {

	@Autowired EmployeeRepository repo;
	@Autowired RoleService roleService;
	@Autowired SkillService skillService;
	
	public Employee save(Employee args, Long id) {
		
		if(id == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		if(args.getFirstname() == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing firstname");
		if(args.getLastname() == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing lastname");
		if(args.getBirthday() == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing birthday");
		if(args.getRoles() == null) args.setRoles(Set.of(Role.builder().roleName(ERole.EMPLOYEE).build()));
		if(repo.existsByTelephone(args.getTelephone())) throw new MyAPIException(HttpStatus.FOUND, "telephone number already used");
		
		args.setIdCredentials(id);
		
		Set<Role> roleList = roleService.gerListRoleFromListRoleName(args.getRoles());
		Set<Skill> skillList = skillService.gerListSkillFromListSkillName(args.getSkills());
		args.setRoles(roleList);
		args.setSkills(skillList);
		return repo.save(args);
	}
	
	public Employee update(Employee args) {
		return repo.save(args);
	}
	
	public Employee findById(Long id) {
		return repo.findById(id).orElseThrow();
	}
	
	public String delById(Long id) {
		repo.deleteById(id);
		return "Skill remuved";
	}
	
	public Page<Employee> findAll(int page, int dim) {
		return repo.findAll(PageRequest.of(page, dim));
	}
}

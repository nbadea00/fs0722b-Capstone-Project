package com.backend.api.user.employee;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.api.user.role.ERole;
import com.backend.api.user.role.Role;
import com.backend.api.user.skill.ESkill;
import com.backend.api.user.skill.Skill;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class EmpolyeeServiceTest {

	@Autowired EmployeeService employeeService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewEmployee() {
		Employee e = Employee.builder()
				.firstname("test")
				.lastname("test")
				.birthday(Date.valueOf("2000-01-13"))
				.telephone("+39 388 407 8217")
				.skills(Set.of(Skill.builder().skillName(ESkill.ANGULAR).build()))
				.roles(Set.of(Role.builder().roleName(ERole.EMPLOYEE).build()))
				.build();
		
		employeeService.save(e, 1l);
		id = e.getId();
		
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdEmployee() {
		employeeService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateIdEmployee() {
		Employee e = employeeService.findById(id);
		e.setFirstname("testNew");
		employeeService.update(e, id);
		
		assertTrue(employeeService.findById(id).getFirstname().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdEmployee() {
		employeeService.delById(id);
		employeeService.findById(id);
	}
}

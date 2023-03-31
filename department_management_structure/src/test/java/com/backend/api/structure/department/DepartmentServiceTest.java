package com.backend.api.structure.department;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class DepartmentServiceTest {

	@Autowired DepartmentService departmentService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewDepartment() {
		Department d = Department.builder()
				.name("test")
				.departmentHeadId(3l)
				.build();
		
		departmentService.save(d);
		id = d.getId();
		
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdDepartment() {
		departmentService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateIdDepartment() {
		Department d = departmentService.findById(id);
		d.setName("testNew");
		departmentService.update(d, id, 3l);
		
		assertTrue(departmentService.findById(id).getName().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdDepartment() {
		departmentService.deleteById(id);
		departmentService.findById(id);
	}
}

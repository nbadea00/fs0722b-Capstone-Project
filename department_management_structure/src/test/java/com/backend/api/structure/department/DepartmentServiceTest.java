package com.backend.api.structure.department;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DepartmentServiceTest {

	@Autowired DepartmentService departmentService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewDepartment() {
		Department d = Department.builder()
				.name("test")
				.build();
		
		departmentService.save(d, 1l);
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
		departmentService.update(d, id);
		
		assertTrue(departmentService.findById(id).getName().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdDepartment() {
		departmentService.deleteById(id);
		departmentService.findById(id);
	}
}

package com.backend.api.structure.section;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.backend.api.structure.department.DepartmentService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class SectionServiceTest {

	@Autowired SectionService sectionService;
	@Autowired DepartmentService departmentService;
	
	private static Long id;
	private final Long idDeoTest = 5l;
	private final Long sectionMaId = 3l;
	public final Long idOperatore = 1l;
	
	@Test
	@Order(1)
	public void saveNewSection() {
		Section s = Section.builder()
				.name("test")
				.sectionManagerId(sectionMaId)
				.department(departmentService.findById(idDeoTest))
				.build();
		
		sectionService.save(s, idOperatore);
		id = s.getId();
		
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdSection() {
		sectionService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateIdSection() {
		Section s = sectionService.findById(id);
		s.setName("testNew");
		sectionService.update(s, id, idOperatore);
		
		assertTrue(sectionService.findById(id).getName().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdSection() {
		sectionService.deleteById(id);
		sectionService.findById(id);
	}
}

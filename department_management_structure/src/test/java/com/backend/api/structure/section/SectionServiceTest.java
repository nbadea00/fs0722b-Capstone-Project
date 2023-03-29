package com.backend.api.structure.section;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class SectionServiceTest {

	@Autowired SectionService sectionService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewSection() {
		Section s = Section.builder()
				.name("test")
				.build();
		
		sectionService.save(s, 1l);
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
		sectionService.update(s, id);
		
		assertTrue(sectionService.findById(id).getName().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdSection() {
		sectionService.deleteById(id);
		sectionService.findById(id);
	}
}

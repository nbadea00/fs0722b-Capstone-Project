package com.backend.api.project.project;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class ProjectServiceTest {

	
	@Autowired ProjectService projectService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewProject() {
		Project p = Project.builder()
				.projectName("testName")
				.projectDescription("testDescr")
				.projectState(ProjectState.PRODUCTION)
				.teamId(1l)
				.build();
		
		projectService.save(p, 1l);
		id = p.getId();
		
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdProject() {
		projectService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateIdProject() {
		Project p = projectService.findById(id);
		p.setProjectDescription("testNew");
		projectService.update(p, id);
		
		assertTrue(projectService.findById(id).getProjectDescription().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdProject() {
		projectService.delById(id);
		projectService.findById(id);
	}
}

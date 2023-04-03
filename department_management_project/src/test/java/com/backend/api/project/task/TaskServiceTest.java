package com.backend.api.project.task;

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
public class TaskServiceTest {
	@Autowired TaskService taskService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewTask() {
		Task t = Task.builder()
				.project(null)
				.taskDescription("descTest")
				.taskState(TaskState.COMPLETED)
				.build();
		
		taskService.save(t, 1l);
		id = t.getId();
		
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdTask() {
		taskService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateIdTask() {
		Task t = taskService.findById(id);
		t.setTaskDescription("testNew");
		taskService.update(t, id);
		
		assertTrue(taskService.findById(id).getTaskDescription().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdTask() {
		taskService.delById(id);
		taskService.findById(id);
	}
}

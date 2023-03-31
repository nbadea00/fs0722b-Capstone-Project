package com.backend.api.user.skill;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.backend.api.user.skill.ESkill;
import com.backend.api.user.skill.Skill;
import com.backend.api.user.skill.SkillService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class SkillServiceTest {

	@Autowired SkillService skillService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewSkillTest() {
		Skill s = new Skill();
		s.setSkillName(ESkill.JUNIT);
		
		skillService.save(s);
		id = s.getId();
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdSkillTest() {
		skillService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateSkillTest() {
		Skill s = skillService.findById(id);
		s.setSkillName(ESkill.JUNIT_TEST);
		skillService.update(s, id);
		assertTrue(skillService.findById(id).getSkillName().equals(ESkill.JUNIT_TEST));
	}
	
	@Test
	@Order(4)
	public void delByIdSkillTest() {
		skillService.delById(id);
		skillService.findById(id);
	}
}

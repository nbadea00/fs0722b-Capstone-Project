package com.backend.api.structure.team;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.backend.api.structure.section.SectionService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class TeamServiceTest {

	@Autowired TeamService teamService;
	@Autowired SectionService sectionService;
	
	private static Long id;
	private final Long idSectionTest = 1l;
	private final Long teamLeadId = 3l;
	public final Long idOperatore = 1l;
	
	@Test
	@Order(1)
	public void saveNewTeam() {
		Team t = Team.builder()
				.teamCode("test")
				.teamLeadId(teamLeadId)
				.section(sectionService.findById(idSectionTest))
				.build();
		
		teamService.save(t, idOperatore);
		id = t.getId();
		
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdTeam() {
		teamService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateIdTeam() {
		Team t = teamService.findById(id);
		t.setTeamCode("testNew");
		teamService.update(t, id, idOperatore);
		
		assertTrue(teamService.findById(id).getTeamCode().equals("testNew"));
	}
	
	@Test
	@Order(4)
	public void delByIdTeam() {
		teamService.deleteById(id);
		teamService.findById(id);
	}
}

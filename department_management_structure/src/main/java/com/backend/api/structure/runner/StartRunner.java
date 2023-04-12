package com.backend.api.structure.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.backend.api.structure.department.Department;
import com.backend.api.structure.department.DepartmentService;
import com.backend.api.structure.section.Section;
import com.backend.api.structure.section.SectionService;
import com.backend.api.structure.team.Team;
import com.backend.api.structure.team.TeamService;

@Component
public class StartRunner implements ApplicationRunner {

	@Autowired DepartmentService departmentService;
	@Autowired SectionService sectionService;
	@Autowired TeamService teamService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Run...");
		if(departmentService.findAll(0, 1).isEmpty()) startDep(1l);
		if(sectionService.findAll(0, 1).isEmpty()) for(Long i = 1l; i < 6l; i++) startSec(i+1);
		if(teamService.findAll(0, 1).isEmpty()) for(Long i = 6l; i < 20l; i++) startTeam(i+1);
	}
	
	public void startDep(Long num) {
		Department d = Department
						.builder()
						.name("department" + num)
						.departmentHeadId(num)
						.build();
		
		departmentService.save(d);
	}
	
	public void startSec(Long num) {
		Section s = Section
				.builder()
				.name("Section" + num)
				.sectionManagerId(num)
				.department(departmentService.findById(1l))
				.build();
	
		sectionService.save(s, 1l);
	}
	
	public void startTeam(Long num) {
		Team t = Team
				.builder()
				.teamCode("tema" + num)
				.section(sectionService.findById(num % 4 + 1))
				.teamLeadId(num)
				.build();
	
		teamService.save(t, 1l);
	}

}

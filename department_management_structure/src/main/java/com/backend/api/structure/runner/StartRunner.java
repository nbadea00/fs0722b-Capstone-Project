package com.backend.api.structure.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.backend.api.structure.department.Department;
import com.backend.api.structure.department.DepartmentService;
import com.backend.api.structure.section.Section;
import com.backend.api.structure.section.SectionService;

@Component
public class StartRunner implements ApplicationRunner {

	@Autowired DepartmentService departmentService;
	@Autowired SectionService sectionService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Run...");
		if(departmentService.findAll(0, 1).isEmpty()) startData();
	}
	
	public void startData() {
		Department d = Department
						.builder()
						.name("testDep")
						.departmentHeadId(1l)
						.build();
		
		departmentService.save(d);
		
		Section s = Section
					.builder()
					.name("testSection")
					.sectionManagerId(2l)
					.department(d)
					.build();
		
		sectionService.save(s, 1l);
	}

}

package com.backend.api.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Role runner...");
		//setRoleDefault();
	}
	
	private void setRoleDefault() {
		Role employee = new Role();
		employee.setRoleName(ERole.EMPLOYEE);
		roleRepository.save(employee);
		
		Role sectionManager = new Role();
		sectionManager.setRoleName(ERole.SECTION_MANAGER);
		roleRepository.save(sectionManager);
		
		Role departementHead = new Role();
		departementHead.setRoleName(ERole.DEPARTEMENT_HEAD);
		roleRepository.save(departementHead);
		
		Role teamLead = new Role();
		teamLead.setRoleName(ERole.TEAM_LEAD);
		roleRepository.save(teamLead);
		
	}

}

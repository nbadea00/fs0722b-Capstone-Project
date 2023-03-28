package com.backend.api.user.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class SkillRunner implements ApplicationRunner {

	@Autowired SkillService skillService;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Skill runner...");
		//setSkillDefault();
	}

	private void setSkillDefault() {
		Skill java = new Skill();
		java.setSkillName(ESkill.JAVA);
		skillService.save(java);
		
		Skill css = new Skill();
		css.setSkillName(ESkill.CSS);
		skillService.save(css);
		
		Skill javascript = new Skill();
		javascript.setSkillName(ESkill.JAVASCRIPT);
		skillService.save(javascript);
		
		Skill html = new Skill();
		html.setSkillName(ESkill.HTML);
		skillService.save(html);
		
		Skill angular = new Skill();
		angular.setSkillName(ESkill.ANGULAR);
		skillService.save(angular);
		
	}
}

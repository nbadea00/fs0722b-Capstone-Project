package com.backend.api.user.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class SkillRunner implements ApplicationRunner {

	@Autowired SkillRepository skillRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Skill runner...");
		if(skillRepository.findAll().size() <= 0) setSkillDefault();
	}

	private void setSkillDefault() {
		Skill java = new Skill();
		java.setSkillName(ESkill.JAVA);
		skillRepository.save(java);
		
		Skill css = new Skill();
		css.setSkillName(ESkill.CSS);
		skillRepository.save(css);
		
		Skill javascript = new Skill();
		javascript.setSkillName(ESkill.JAVASCRIPT);
		skillRepository.save(javascript);
		
		Skill html = new Skill();
		html.setSkillName(ESkill.HTML);
		skillRepository.save(html);
		
		Skill angular = new Skill();
		angular.setSkillName(ESkill.ANGULAR);
		skillRepository.save(angular);
		
	}
}

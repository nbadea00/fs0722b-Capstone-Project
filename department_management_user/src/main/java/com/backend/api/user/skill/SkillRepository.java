package com.backend.api.user.skill;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

	public Optional<Skill> findBySkillName(ESkill skillName);
}

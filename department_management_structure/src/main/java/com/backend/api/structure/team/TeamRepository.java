package com.backend.api.structure.team;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {
	boolean existsByTeamCodeAndIdNot(String teamCode, Long id);

	boolean existsByTeamCode(String teamCode);
	
	Optional<Team> findByTeamCode(String teamCode);
	
	Optional<Team> findByTeamLeadId(Long teamLeadId);
	
	@Query("SELECT t FROM Team t WHERE t.section.id = :sectionId")
	Page<Team> findBySectionId(Pageable page,Long sectionId);
	
	@Query("SELECT t FROM Team t WHERE t.mainTeam.id = :mainTeamId")
	Page<Team> findByMainTeamId(Pageable page, Long mainTeamId);
}

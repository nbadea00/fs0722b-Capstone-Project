package com.backend.api.structure.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.structure.exception.MyAPIException;
import com.backend.api.structure.exception.ResourceNotFoundException;
import com.backend.api.structure.section.SectionService;

@Service
public class TeamService {

	@Autowired TeamRepository repo;
	@Autowired SectionService sectionService;
	
	public Team save(Team args, Long idUser) {
		if(args.getTeamLeadId() == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		
		if(repo.existsByTeamCode(args.getTeamCode())) throw new MyAPIException(HttpStatus.NOT_FOUND, "Team code already used");	
		
		if(!sectionService.getAuth(idUser, args.getSection())) throw new MyAPIException(HttpStatus.UNAUTHORIZED, "not authorized");
		
		return repo.save(args);
	}
	
	public Team update(Team args, Long id, Long idUser) {
		if(id == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Team", "id", id.toString()));
		
		if(repo.existsByTeamCodeAndIdNot(args.getTeamCode(), id)) throw new MyAPIException(HttpStatus.NOT_FOUND, "Team code already used");
		
		if(!getAuth(idUser, args)) throw new MyAPIException(HttpStatus.UNAUTHORIZED, "not authorized");
		
		return repo.save(args);
	}
	
	public Team findById(Long id) {
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Team", "id", id.toString()));
	}
	
	public Team findByName(String teamCode) {
		return repo.findByTeamCode(teamCode).orElseThrow(()-> new ResourceNotFoundException("Team", "teamCode", teamCode));
	}
	
	public List<Team> findBySectionId(Long sectionId, int page, int dim) {
		return repo.findBySectionId(PageRequest.of(page, dim), sectionId).toList();
	}
	
	public Team findByTeamLeadId(Long teamLeadId) {
		return repo.findByTeamLeadId(teamLeadId).orElseThrow(()-> new ResourceNotFoundException("Team", "TeamManagerId", teamLeadId.toString()));
	}
	
	public List<Team> findAll(int page, int dim) {
		return repo.findAll(PageRequest.of(page, dim)).toList();
	}
	
	public String deleteById(Long id) {
		repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Team", "id", id.toString()));
		repo.deleteById(id);
		return "Team remuved";
	}
	
	public Boolean getAuth(Long idUser, Team team) {
		boolean auth = false;
		Team main = team;
		if(main == null) return false;
		do {
			auth = repo.findByTeamLeadId(idUser).equals(repo.findById(main.getId()));
			if(main.getMainTeam() != null) main = main.getMainTeam();
		}while(!auth && main.getMainTeam() != null);
		
		return auth || repo.findByTeamLeadId(idUser).equals(repo.findById(main.getId())) || sectionService.getAuth(idUser, team.getSection());
	}
}

package com.backend.api.structure.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/api/teams")
public class TeamController {

	@Autowired TeamService teamService;
	
	@GetMapping
	public ResponseEntity<List<Team>> getAllTeam(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "0") int dim){
		return ResponseEntity.ok(teamService.findAll(page, dim));
	}
	
	@GetMapping("/findBySectionId")
	public ResponseEntity<List<Team>> getAllTeamBySectionId(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "0") int dim,
			@RequestParam(name = "sectionId") Long sectionId){
		return ResponseEntity.ok(teamService.findBySectionId(sectionId, page, dim));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Team> getTeamById(@PathVariable Long id){
		return ResponseEntity.ok(teamService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Team> postTeam(@RequestBody Team team, 
			@RequestParam(name = "idUser") Long idUser){
		return ResponseEntity.ok(teamService.save(team, idUser));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Team> putTeam(
			@RequestBody Team team, 
			@PathVariable Long id,
			@RequestParam(name = "idUser") Long idUser){
		return ResponseEntity.ok(teamService.update(team, id, idUser));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delTeam(@PathVariable Long id){
		return ResponseEntity.ok(teamService.deleteById(id));
	}
	
	@PostMapping("/auth")
	public ResponseEntity<Boolean> getAuth(
			@RequestParam(name = "id") Long id,
			@RequestBody Team team){
		return ResponseEntity.ok(teamService.getAuth(id, team));
	}
}

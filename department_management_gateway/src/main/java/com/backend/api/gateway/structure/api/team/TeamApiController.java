package com.backend.api.gateway.structure.api.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/teams")
public class TeamApiController {
	@Autowired TeamApiService teamApiService;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<TeamDto> postTeam(@RequestBody TeamDto team){
		return ResponseEntity.ok(
				teamApiService
				.createNewTeam(team));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<TeamDto> putTeam(
			@RequestBody TeamDto team, 
			@PathVariable Long id){
		return ResponseEntity.ok(
				teamApiService
				.modifyTeamByAdmin(id, team));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Page<TeamDto>> getAllTeama(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "dim", defaultValue = "5") int dim){
		return ResponseEntity.ok(
				teamApiService
				.getAllTeam(page, dim));
				
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id){
		return ResponseEntity.ok(
				teamApiService
				.getTeamById(id));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> delTeam(@PathVariable Long id){
		return ResponseEntity.ok(
				teamApiService
				.deleteTeamById(id));
	}
}

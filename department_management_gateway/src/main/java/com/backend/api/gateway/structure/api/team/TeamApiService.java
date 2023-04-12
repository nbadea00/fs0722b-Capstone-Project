package com.backend.api.gateway.structure.api.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.backend.api.gateway.auth.exception.MyAPIException;
import com.backend.api.gateway.employee.api.EmployeeApiService;
import com.backend.api.gateway.webclient.WebClientService;

import reactor.core.publisher.Mono;

@Service
public class TeamApiService {
	@Autowired WebClientService webClientService;
	@Autowired EmployeeApiService employeeApiService;
	@Autowired WebClient webClientTeamsApi;
	
	public TeamDto createNewTeam(TeamDto team) {
		if(employeeApiService.getAuth(
				team.getTeamLeadId(),
				"TEAM_LEAD")) throw new MyAPIException(HttpStatus.UNAUTHORIZED, "not authorized");
		return webClientService
				.errorHandleResponse(
					webClientTeamsApi
					.post()
					.body(Mono.just(team),TeamDto.class)
					.retrieve())
				.bodyToMono(TeamDto.class)
				.block();
	}
	
	public TeamDto modifyTeamByAdmin(Long idTeam, TeamDto team) {
		return webClientService
				.errorHandleResponse(
					webClientTeamsApi
					.put()
					.uri("/" + idTeam)
					.body(Mono.just(team),TeamDto.class)
					.retrieve())
				.bodyToMono(TeamDto.class)
				.block();
	}
	
	public List<TeamDto> getAllTeam(int page, int dim) {
		return webClientService
				.errorHandleResponse(
					webClientTeamsApi
					.get()
					.uri("?page=" + page + "&dim=" + dim)
					.retrieve())
				.bodyToMono(new ParameterizedTypeReference<List<TeamDto>>() {})
				.block();		
	}
	
	public List<TeamDto> getAllTeamBySectionId(int page, int dim, Long sectionId) {
		return webClientService
				.errorHandleResponse(
					webClientTeamsApi
					.get()
					.uri("/findBySectionId?page=" + page + "&dim=" + dim+ "&sectionId=" + sectionId)
					.retrieve())
				.bodyToMono(new ParameterizedTypeReference<List<TeamDto>>() {})
				.block();		
	}
	
	public TeamDto getTeamById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientTeamsApi
					.get()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(TeamDto.class)
				.block();		
	}
	
	public String deleteTeamById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientTeamsApi
					.delete()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(String.class)
				.block();		
	}
}

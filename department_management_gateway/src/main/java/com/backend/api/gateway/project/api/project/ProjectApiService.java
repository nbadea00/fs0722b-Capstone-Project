package com.backend.api.gateway.project.api.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.backend.api.gateway.webclient.WebClientService;

import reactor.core.publisher.Mono;

@Service
public class ProjectApiService {

	@Autowired WebClient webClientProjectApi;
	@Autowired WebClientService webClientService;
	
	public ProjectDto createNewProject(ProjectDto project, Long id) {
		return webClientService
				.errorHandleResponse(
					webClientProjectApi
					.post()
					.uri("?id=" + id)
					.body(Mono.just(project),ProjectDto.class)
					.retrieve())
				.bodyToMono(ProjectDto.class)
				.block();
	}
	
	public ProjectDto modifyProjectByAdmin(Long idProject, ProjectDto project, Long idUser) {
		return webClientService
				.errorHandleResponse(
					webClientProjectApi
					.put()
					.uri("/" + idProject + "?idUser=" + idUser)
					.body(Mono.just(project),ProjectDto.class)
					.retrieve())
				.bodyToMono(ProjectDto.class)
				.block();
	}
	
	public Page<ProjectDto> getAllProject(int page, int dim) {
		return webClientService
				.errorHandleResponse(
						webClientProjectApi
						.get()
						.uri("?page=" + page + "&dim=" + dim)
						.retrieve())
				.bodyToMono(new ParameterizedTypeReference<Page<ProjectDto>>() {})
				.block();		
	}
	
	public ProjectDto getProjectById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientProjectApi
					.get()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(ProjectDto.class)
				.block();		
	}
	
	public String deleteProjectById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientProjectApi
					.delete()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(String.class)
				.block();		
	}
}

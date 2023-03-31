package com.backend.api.gateway.project.api.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.backend.api.gateway.webclient.WebClientService;

import reactor.core.publisher.Mono;

@Service
public class TaskApiService {
	@Autowired WebClient webClientTaskApi;
	@Autowired WebClientService webClientService;
	
	public TaskDto createNewTask(TaskDto task, Long id) {
		return webClientService
				.errorHandleResponse(
					webClientTaskApi
					.post()
					.uri("?id=" + id)
					.body(Mono.just(task),TaskDto.class)
					.retrieve())
				.bodyToMono(TaskDto.class)
				.block();
	}
	
	public TaskDto modifyTaskByAdmin(Long idTask, TaskDto task, Long idUser) {
		return webClientService
				.errorHandleResponse(
					webClientTaskApi
					.put()
					.uri("/" + idTask + "?idUser=" + idUser)
					.body(Mono.just(task),TaskDto.class)
					.retrieve())
				.bodyToMono(TaskDto.class)
				.block();
	}
	
	public Page<TaskDto> getAllTask(int page, int dim) {
		return webClientService
				.errorHandleResponse(
						webClientTaskApi
						.get()
						.uri("?page=" + page + "&dim=" + dim)
						.retrieve())
				.bodyToMono(new ParameterizedTypeReference<Page<TaskDto>>() {})
				.block();		
	}
	
	public TaskDto getTaskById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientTaskApi
					.get()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(TaskDto.class)
				.block();		
	}
	
	public String deleteTaskById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientTaskApi
					.delete()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(String.class)
				.block();		
	}
}

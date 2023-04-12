package com.backend.api.gateway.structure.api.section;

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
public class SectionApiService {

	@Autowired WebClientService webClientService;
	@Autowired EmployeeApiService employeeApiService;
	@Autowired WebClient webClientSectionsApi;
	
	public SectionDto createNewSection(SectionDto section) {
		if(employeeApiService.getAuth(
				section.getSectionManagerId(),
				"SECTION_MANAGER")) throw new MyAPIException(HttpStatus.UNAUTHORIZED, "not authorized");
		return webClientService
				.errorHandleResponse(
					webClientSectionsApi
					.post()
					.body(Mono.just(section),SectionDto.class)
					.retrieve())
				.bodyToMono(SectionDto.class)
				.block();
	}
	
	public SectionDto modifySectionByAdmin(Long idSection, SectionDto section) {
		return webClientService
				.errorHandleResponse(
					webClientSectionsApi
					.put()
					.uri("/" + idSection)
					.body(Mono.just(section),SectionDto.class)
					.retrieve())
				.bodyToMono(SectionDto.class)
				.block();
	}
	
	public List<SectionDto> getAllSection(int page, int dim) {
		return webClientService
				.errorHandleResponse(
					webClientSectionsApi
					.get()
					.uri("?page=" + page + "&dim=" + dim)
					.retrieve())
				.bodyToMono(new ParameterizedTypeReference<List<SectionDto>>() {})
				.block();		
	}
	
	public List<SectionDto> getAllSectionByDepartmentId(int page, int dim, Long departmentId) {
		return webClientService
				.errorHandleResponse(
					webClientSectionsApi
					.get()
					.uri("/findByDepartmentId?page=" + page + "&dim=" + dim + "&departmentId=" + departmentId)
					.retrieve())
				.bodyToMono(new ParameterizedTypeReference<List<SectionDto>>() {})
				.block();		
	}
	
	public SectionDto getSectionById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientSectionsApi
					.get()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(SectionDto.class)
				.block();		
	}
	
	public String deleteSectionById(Long id) {
		return webClientService
				.errorHandleResponse(
					webClientSectionsApi
					.delete()
					.uri("/" + id)
					.retrieve())
					.bodyToMono(String.class)
				.block();		
	}
}

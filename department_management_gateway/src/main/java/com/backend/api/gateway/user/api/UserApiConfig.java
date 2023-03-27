package com.backend.api.gateway.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApiConfig {
	
	@Autowired RestTemplateBuilder builder;

	public RestTemplate getRestTemplate() {
		return builder.rootUri("http://localhost:8081/api").build();
	}
}

package com.backend.api.gateway.webclient;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.backend.api.gateway.auth.exception.MyAPIException;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	public ResponseSpec errorHandleResponse (ResponseSpec responseSpec) {
		return responseSpec
				.onStatus(HttpStatusCode::is4xxClientError, r -> {
					return r.bodyToMono(String.class).flatMap(e -> {
						return Mono.error(new MyAPIException(HttpStatus.valueOf(r.statusCode().value()), e));
					});
				})
				.onStatus(HttpStatusCode::is5xxServerError, r -> {
					return r.bodyToMono(String.class).flatMap(error -> {
						return Mono.error(new MyAPIException(HttpStatus.valueOf(r.statusCode().value()), error));
					});
				});
	}
}

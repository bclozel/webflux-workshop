package io.spring.workshop.stockquotes;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class QuoteHandler {

	public Mono<ServerResponse> hello(ServerRequest request) {
		return ok().contentType(TEXT_PLAIN)
				.body(BodyInserters.fromObject("Hello Spring!"));
	}
}

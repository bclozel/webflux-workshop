package io.spring.workshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static java.time.Duration.ofMillis;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class QuoteHandler {

	private final Flux<Quote> quoteStream;

	public QuoteHandler(QuoteGenerator quoteGenerator) {
		this.quoteStream = quoteGenerator.fetchQuoteStream(ofMillis(200)).share();
	}

	public Mono<ServerResponse> streamQuotes(ServerRequest request) {
		return ok()
				.contentType(APPLICATION_STREAM_JSON)
				.body(this.quoteStream, Quote.class);
	}

	public Mono<ServerResponse> fetchQuotes(ServerRequest request) {
		int size = Integer.parseInt(request.queryParam("size").orElse("10"));
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.quoteStream.take(size), Quote.class);
	}

}
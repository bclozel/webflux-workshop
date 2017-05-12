package io.spring.workshop.tradingservice.websocket;

import java.time.Duration;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

public class EchoWebSocketHandler implements WebSocketHandler {

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.send(session.receive()
				.doOnNext(WebSocketMessage::retain)
				.delayElements(Duration.ofSeconds(1)).log());
	}
}

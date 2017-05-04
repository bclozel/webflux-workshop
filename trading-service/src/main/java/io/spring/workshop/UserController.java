package io.spring.workshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	private final TradingUserRepository tradingUserRepository;

	public UserController(TradingUserRepository tradingUserRepository) {
		this.tradingUserRepository = tradingUserRepository;
	}

	@GetMapping(path="/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Flux<TradingUser> listUsers() {
		return this.tradingUserRepository.findAll();
	}

	@GetMapping(path="/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Mono<TradingUser> showUsers(@PathVariable String username) {
		return this.tradingUserRepository.findByUserName(username);
	}


}

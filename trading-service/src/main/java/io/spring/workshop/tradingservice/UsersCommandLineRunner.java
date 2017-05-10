package io.spring.workshop.tradingservice;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsersCommandLineRunner implements CommandLineRunner {

	private final TradingUserRepository repository;

	public UsersCommandLineRunner(TradingUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		List<TradingUser> users = Arrays.asList(
				new TradingUser("sdeleuze", "Sebastien Deleuze"),
				new TradingUser("snicoll", "Stephane Nicoll"),
				new TradingUser("rstoyanchev", "Rossen Stoyanchev"),
				new TradingUser("poutsma", "Arjen Poutsma"),
				new TradingUser("smaldini", "Stephane Maldini"),
				new TradingUser("simonbasle", "Simon Basle"),
				new TradingUser("violetagg", "Violeta Georgieva"),
				new TradingUser("bclozel", "Brian Clozel")
		);
		this.repository.insert(users).blockLast(Duration.ofSeconds(3));
	}

}

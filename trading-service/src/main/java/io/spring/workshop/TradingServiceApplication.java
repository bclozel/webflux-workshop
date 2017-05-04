package io.spring.workshop;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TradingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner createUsers(TradingUserRepository tradingUserRepository) {
		return strings -> {
			List<TradingUser> users = Arrays.asList(
					new TradingUser("sdeleuze", "Sebastien Deleuze"),
					new TradingUser("snicoll", "Stephane Nicoll"),
					new TradingUser("rstoyanchev", "Rossen Stoyanchev"),
					new TradingUser("poutsma", "Arjen Poutsma"),
					new TradingUser("smaldini", "Stephane Maldini"),
					new TradingUser("simonbasle", "Simon Basle"),
					new TradingUser("bclozel", "Brian Clozel")
			);
			tradingUserRepository.insert(users).blockLast(Duration.ofSeconds(3));
		};
	}
}

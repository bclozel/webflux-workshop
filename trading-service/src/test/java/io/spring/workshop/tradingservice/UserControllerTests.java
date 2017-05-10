package io.spring.workshop.tradingservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
public class UserControllerTests {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private TradingUserRepository repository;

  @Test
  public void listUsers() {
    TradingUser juergen = new TradingUser("1", "jhoeller", "Juergen Hoeller");
    TradingUser andy = new TradingUser("2", "wilkinsona", "Andy Wilkinson");

    BDDMockito.given(this.repository.findAll())
        .willReturn(Flux.just(juergen, andy));

    this.webTestClient.get().uri("/users").accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectBodyList(TradingUser.class)
        .hasSize(2)
        .contains(juergen, andy);

  }

  @Test
  public void showUser() {
    TradingUser juergen = new TradingUser("1", "jhoeller", "Juergen Hoeller");

    BDDMockito.given(this.repository.findByUserName("jhoeller"))
        .willReturn(Mono.just(juergen));

    this.webTestClient.get().uri("/users/jhoeller").accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectBody(TradingUser.class)
        .isEqualTo(juergen);
  }

}


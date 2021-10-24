package de.neuefische.reactivetask;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
@AutoConfigureWebTestClient
class ReactiveTaskApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void integrationTest() {
		
		var createdOrder1 = webTestClient
				.post()
				.uri("/api/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(new OrderDTO("PIZZA")))
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(OrderDTO.class)
				.returnResult()
				.getResponseBody();
		
		var createdOrder2 = webTestClient
				.post()
				.uri("/api/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(new OrderDTO("PASTA")))
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(OrderDTO.class)
				.returnResult()
				.getResponseBody();
		
		var allOrders = webTestClient
				.get()
				.uri("/api/orders")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBodyList(OrderDTO.class)
				.returnResult()
				.getResponseBody();

		assertThat(allOrders).hasSize(2);
		assertThat(createdOrder1).isEqualTo(allOrders.get(0));
		assertThat(createdOrder2).isEqualTo(allOrders.get(1));
		
	}

}

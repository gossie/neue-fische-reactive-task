package de.neuefische.reactivetask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReactiveTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveTaskApplication.class, args);
	}
	
	@Bean
	public WebClient webClient(@Value("${payment.service.url:http://localhost:5000}") String paymentServiceUrl) {
		return WebClient.create(paymentServiceUrl);
	}

}

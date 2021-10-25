package de.neuefische.reactivetask;

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
	public WebClient webClient() {
		return WebClient.create("https://herokuapps.com/ldwas-payment-service");
	}

}

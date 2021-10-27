package de.neuefische.reactivetask;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
@RequiredArgsConstructor
class OrderController {
	
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final WebClient webClient;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<OrderDTO> createOrder(@RequestBody OrderDTO order) {
		// TODO: implement me
		// Verwendet das orderRepository um die übergebene Order in die Datenbank zu schreiben.
		// Beachtet, dass ihr hier ein OrderDTO und auch wieder zurückgeben müsst, das orderRepository
		// aber eine Order erwartet und zurückgibt.
		
		return orderRepository.save(orderMapper.toOrder(order))
				.map(orderMapper::toOrderDTO);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<OrderDTO> getOrders() {
		// TODO: implement me
		// Verwendet das OrderRepository um alle Orders aus der Datenbank auszulesen und als OrderDTO
		// Objekte zurückzugeben.
		
	    return orderRepository.findAll()
	    		.map(orderMapper::toOrderDTO);
	}
	
	@PostMapping(path = "/{id}/payment", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<OrderDTO> payment(@PathVariable String id) {
		// TODO: implement me
		// Jetzt geht es ans eingemachte. Verwendet den WebClient um den Pfad "/api/payment" per POST mit einem PaymentRequest auf.
		// Als Ergebnis bekommt ihr ein PaymentResult. Markiert die Order als bezahlt indem ihr die Mehtode
		// markAsPayed mit dem Preis aus dem PaymentResult aufruft.
		// Speichert dann die geänderte Order wieder in der Datenbank und gebt das Ergebnis als OrderDTO zurück.
		// Am WebClient ist die baseUrl schon korrekt konfiguriert.
		
		return orderRepository.findById(id)
				.map(order -> new PaymentRequest(order.getId(), order.getItem()))
				.flatMap(this::callPaymentService) // flatMap, da man sonst ein Mono<Mono<PaymentResponse>> hat. Das flatMap eliminiert das innere Mono 
				.flatMap(this::markOrderAsPayed)
				.map(orderMapper::toOrderDTO);
	}
	
	private Mono<Order> markOrderAsPayed(PaymentResponse paymentResponse) {
		return orderRepository.findById(paymentResponse.getOrderId())
				.flatMap(order -> orderRepository.save(order.markAsPayed(paymentResponse.getPrice())));
	}
	
	private Mono<PaymentResponse> callPaymentService(PaymentRequest paymentRequest) {
		return webClient
				.post()
				.uri("/api/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(paymentRequest))
				.exchangeToMono(clientResponse -> clientResponse.bodyToMono(PaymentResponse.class));
	}

}

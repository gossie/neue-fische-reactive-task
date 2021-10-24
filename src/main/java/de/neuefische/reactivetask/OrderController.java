package de.neuefische.reactivetask;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
class OrderController {
	
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<OrderDTO> createOrder(@RequestBody OrderDTO order) {
		// TODO: implement me
		return Mono.empty();
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<OrderDTO> getOrders() {
		// TODO: implement me
	    return Flux.empty();
	}

}

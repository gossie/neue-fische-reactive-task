package de.neuefische.reactivetask;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
class OrderMapper {

	OrderDTO toOrderDTO(Order order) {
		return new OrderDTO(order.getItem().name(), order.isPayed(), order.getPrice(), Map.of("self", "/api/orders/" + order.getId(), "payment", "/api/orders/" + order.getId() + "/payment"));
	}
	
	Order toOrder(OrderDTO orderDTO) {
		return new Order(null, Item.valueOf(orderDTO.getItem()), orderDTO.isPayed(), orderDTO.getPrice());
	}
	
}

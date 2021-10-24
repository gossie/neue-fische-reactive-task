package de.neuefische.reactivetask;

import org.springframework.stereotype.Component;

@Component
class OrderMapper {

	OrderDTO toOrderDTO(Order order) {
		return new OrderDTO(order.item().name());
	}
	
	Order toOrder(OrderDTO orderDTO) {
		return new Order(null, Item.valueOf(orderDTO.item()));
	}
	
}

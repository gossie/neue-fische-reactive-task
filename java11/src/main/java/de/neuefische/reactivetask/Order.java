package de.neuefische.reactivetask;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Document
@RequiredArgsConstructor
@Getter
class Order {
	
	@Id
	private final String id;
	private final Item item;
	private final boolean payed;
	private final double price;
	
	Order markAsPayed(double price) {
		return new Order(id, item, true, price);
	}
	
}

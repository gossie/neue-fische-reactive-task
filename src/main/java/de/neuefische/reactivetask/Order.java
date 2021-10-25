package de.neuefische.reactivetask;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
record Order(@Id String id, Item item, boolean payed, double price) {
	
	Order markAsPayed(double price) {
		return new Order(id, item, true, price);
	}
	
}

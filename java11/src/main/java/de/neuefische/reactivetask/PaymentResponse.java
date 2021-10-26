package de.neuefische.reactivetask;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
class PaymentResponse {

	private final String orderId;
	private final String item;
	private final double price;
	
	public PaymentResponse(
			@JsonProperty("orderId") String orderId,
			@JsonProperty("item") String item,
			@JsonProperty("price") double price) {
		this.orderId = orderId;
		this.item = item;
		this.price = price;
	}
	
}

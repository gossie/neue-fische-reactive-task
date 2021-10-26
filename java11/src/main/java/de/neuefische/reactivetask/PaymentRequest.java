package de.neuefische.reactivetask;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
class PaymentRequest {
	
	private final String orderId;
	private final Item item;
	
	public PaymentRequest(@JsonProperty("orderId") String orderId, @JsonProperty("item") Item item) {
		this.orderId = orderId;
		this.item = item;
	}
	
}

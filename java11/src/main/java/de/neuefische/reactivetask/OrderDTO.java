package de.neuefische.reactivetask;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
class OrderDTO {

	private final String item;
	private final boolean payed;
	private final double price;
	private final Map<String, String> links;
	
	public OrderDTO(
			@JsonProperty("item") String item,
			@JsonProperty("payed") boolean payed,
			@JsonProperty("price") double price,
			@JsonProperty("link") Map<String, String> links) {
		this.item = item;
		this.payed = payed;
		this.price = price;
		this.links = links;
	}
	
	
	
}

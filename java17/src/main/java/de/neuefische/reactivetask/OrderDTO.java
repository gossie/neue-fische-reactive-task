package de.neuefische.reactivetask;

import java.util.Map;

record OrderDTO(String item, boolean payed, double price, Map<String, String> links) {

}

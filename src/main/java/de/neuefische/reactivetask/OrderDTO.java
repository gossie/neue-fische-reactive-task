package de.neuefische.reactivetask;

import java.util.Map;

record OrderDTO(String item, boolean payed, Map<String, String> links) {

}

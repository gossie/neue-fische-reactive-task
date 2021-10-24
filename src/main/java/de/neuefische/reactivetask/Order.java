package de.neuefische.reactivetask;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
record Order(@Id String id, Item item) {
}

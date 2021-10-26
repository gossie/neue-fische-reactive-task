package de.neuefische.reactivetask;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}

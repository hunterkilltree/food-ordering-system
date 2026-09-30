package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

// Output port for OrderCreatedEvent specifically (see DomainEventPublisher's
// Javadoc for why the domain layer only declares an interface here). One
// dedicated interface per event type — rather than a single publisher
// generic over any DomainEvent — lets Spring wire a distinct adapter
// (a distinct Kafka topic/producer) for each event, and lets
// OrderCreateCommandHandler depend on exactly the publisher it needs.
public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}

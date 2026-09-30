package com.food.ordering.system.domain.event.publisher;

import com.food.ordering.system.domain.event.DomainEvent;

/**
 * Output port (hexagonal/ports-and-adapters architecture): the domain layer
 * declares that it needs to publish events, without knowing or depending on
 * how (Kafka, an outbox table, an in-memory bus, ...). A messaging-adapter
 * module elsewhere implements this interface per event type — e.g.
 * OrderCreatedPaymentRequestMessagePublisher extends
 * DomainEventPublisher&lt;OrderCreatedEvent&gt; — and gets wired in by
 * Spring. This is what keeps order-domain-core/order-application-service
 * free of any Kafka (or other transport) dependency.
 */
public interface DomainEventPublisher <T extends DomainEvent> {

    void publish(T domainEvent);
}

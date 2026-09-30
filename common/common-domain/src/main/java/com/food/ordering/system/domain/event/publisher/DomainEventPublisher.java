package com.food.ordering.system.domain.event.publisher;

import com.food.ordering.system.domain.event.DomainEvent;

// Output port: declares that an event needs publishing without depending
// on how (Kafka, outbox, ...). Implemented per event type elsewhere.
public interface DomainEventPublisher <T extends DomainEvent> {

    void publish(T domainEvent);
}

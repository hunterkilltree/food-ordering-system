package com.food.ordering.system.domain.event;

// Marker for a domain event (something that already happened, e.g.
// OrderCreatedEvent). T is the aggregate the event is about.
public interface DomainEvent<T> {

}

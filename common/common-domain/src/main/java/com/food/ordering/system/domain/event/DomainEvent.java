package com.food.ordering.system.domain.event;

/**
 * Marker interface for a domain event: something that already happened in
 * the domain (past tense, e.g. OrderCreatedEvent) that other parts of the
 * system may want to react to. T is the aggregate type the event is about
 * (see OrderEvent implements DomainEvent&lt;Order&gt;) — it isn't used for
 * any method here, but it lets a generic consumer like
 * DomainEventPublisher&lt;T extends DomainEvent&gt; stay type-safe about
 * which aggregate an event publisher handles.
 */
public interface DomainEvent<T> {

}

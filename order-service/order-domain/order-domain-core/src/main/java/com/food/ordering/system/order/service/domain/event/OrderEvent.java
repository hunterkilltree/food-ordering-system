package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.order.service.domain.entity.Order;

// Base class shared by OrderCreatedEvent/OrderPaidEvent/OrderCancelledEvent:
// every order-related event carries the same two things (which order, and
// when), so that's factored up here rather than repeated in each subclass.
// Separate concrete subclasses (instead of one OrderEvent with an
// eventType field) exist so a listener can depend on exactly the event
// type it cares about — e.g. an OrderCreatedPaymentRequestMessagePublisher
// implements DomainEventPublisher<OrderCreatedEvent> and the compiler
// guarantees it can never be handed an OrderCancelledEvent by mistake.
public abstract class OrderEvent implements DomainEvent<Order> {

    private final Order order;
    private final ZonedDateTime createdAt;

    public OrderEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}

package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.order.service.domain.entity.Order;

// Fired from OrderDomainService.validateInitialOrder() once an order has
// passed validation and been initialized (id/trackingId assigned, status
// set to PENDING). Consumed downstream by
// OrderCreatedPaymentRequestMessagePublisher to kick off payment.
public class OrderCreatedEvent extends OrderEvent {

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}

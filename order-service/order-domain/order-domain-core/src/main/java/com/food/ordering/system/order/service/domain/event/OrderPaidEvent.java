package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.order.service.domain.entity.Order;

// Fired from OrderDomainService.payOrder() once payment succeeds and the
// order moves PENDING -> PAID. Consumed downstream by
// OrderPaidRestaurantRequestMessagePublisher to ask the restaurant to
// approve the order.
public class OrderPaidEvent extends OrderEvent {

    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}

package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.order.service.domain.entity.Order;

// Fired from OrderDomainService.cancelOrderPayment() when a payment needs
// to be reversed. Consumed downstream by
// OrderCancelledPaymentRequestMessagePublisher to ask the payment service
// to refund it.
public class OrderCancelledEvent extends OrderEvent {

    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}

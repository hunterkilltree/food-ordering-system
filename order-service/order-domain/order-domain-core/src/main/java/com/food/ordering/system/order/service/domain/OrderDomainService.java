package com.food.ordering.system.order.service.domain;

import java.util.List;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

/**
 * Return domain event form the domain service
 * This means the event firing process will be on the called service
 * which will be on the caller service 
 * The events will still created in the domain core either in entity or in domain service
 * However, the event firing will be in the application service
 * Because before firing an event, the underlying business operation
 * should be persisted in db
 * Why don't fire event on domain service or entity? I need to persist the business logic results
 * However, like to deligate to the application service to prevent unnessary job form domain core module
 * which should only focus on business
 * Where to fire the event? In the application service. Domain layer should not know about how to fire the event.
 * Where to create the event? Domain Service or Entities
 */

public interface OrderDomainService {

    OrderCreatedEvent validateInitialOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}

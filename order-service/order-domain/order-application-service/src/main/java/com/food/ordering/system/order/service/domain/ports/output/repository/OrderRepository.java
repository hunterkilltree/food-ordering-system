package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

// Output port for Order persistence — see CustomerRepository's comment for
// the Dependency Inversion rationale. Looked up by TrackingId rather than
// the internal OrderId because that's the id exposed to and used by
// callers (see TrackOrderQuery/CreateOrderResponse).
public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}

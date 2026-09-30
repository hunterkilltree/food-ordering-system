package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;

// Aggregate root for this bounded context's view of a customer. It's a
// public constructor (not a Builder, unlike Order/OrderItem/Restaurant)
// because it wraps a single field — id — with nothing else yet to
// validate or assemble; order-service only ever needs to check that a
// customer id exists (see CustomerRepository.findCustomer), it doesn't
// own customer data, so there's no reason for a heavier construction API.
public class Customer extends AggregateRoot<CustomerId> {

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}

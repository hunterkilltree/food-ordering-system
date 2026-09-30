package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

// Output port for persistence (Dependency Inversion Principle: the domain
// layer defines what it needs from storage as an interface; the
// order-dataaccess module implements it, e.g. against JPA/Postgres — this
// module never depends on that infrastructure directly). Optional as the
// return type makes "not found" an explicit case callers must handle,
// instead of returning null and risking a NullPointerException — see
// OrderCreateCommandHandler.checkCustomer, which turns an empty Optional
// into an OrderDomainException.
public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}

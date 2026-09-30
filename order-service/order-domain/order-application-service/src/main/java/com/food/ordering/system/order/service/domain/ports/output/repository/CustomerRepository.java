package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

// Output port for persistence (Dependency Inversion: order-dataaccess
// implements this). Optional return makes "not found" explicit.
public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}

package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Restaurant;

import java.util.Optional;

// Output port for restaurant lookups — see CustomerRepository. Takes a
// Restaurant, not just an id, since the query also needs the product ids.
public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}

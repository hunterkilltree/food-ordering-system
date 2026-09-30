package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Restaurant;

import java.util.Optional;

// Output port for restaurant lookups — see CustomerRepository's comment for
// the Dependency Inversion rationale. Takes a Restaurant (not just a
// RestaurantId) because the query also needs to know which product ids to
// fetch pricing/name info for — see OrderDataMapper.createOrderCommandToRestaurant,
// which builds that lookup-shaped Restaurant from the incoming command.
public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}

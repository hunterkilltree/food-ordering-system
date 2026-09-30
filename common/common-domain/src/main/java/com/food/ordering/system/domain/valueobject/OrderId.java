package com.food.ordering.system.domain.valueobject;


import java.util.UUID;

// A type-safe wrapper around a UUID — see BaseId's Javadoc for why this
// exists instead of passing raw UUIDs around. CustomerId/ProductId/
// RestaurantId in this same package follow the identical pattern.
public class OrderId extends BaseId<UUID> {
    public OrderId(UUID value) {
        super(value);
    }
}


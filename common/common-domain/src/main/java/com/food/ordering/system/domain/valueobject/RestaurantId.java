package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

// Type-safe UUID wrapper — see BaseId and OrderId for the full rationale.
public class RestaurantId extends BaseId<UUID> {
    public RestaurantId(UUID value) {
        super(value);
    }

}

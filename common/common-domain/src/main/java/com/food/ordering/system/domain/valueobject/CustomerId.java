package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

// Type-safe UUID wrapper — see BaseId and OrderId for the full rationale.
public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID id) {
        super(id);
    }
}

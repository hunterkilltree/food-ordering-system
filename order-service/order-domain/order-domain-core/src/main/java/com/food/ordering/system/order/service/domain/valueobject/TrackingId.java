package com.food.ordering.system.order.service.domain.valueobject;

import java.util.UUID;

import com.food.ordering.system.domain.entity.BaseEntity;


public class TrackingId extends BaseEntity<UUID> {
    public TrackingId(UUID id) {
        // Same reasoning as OrderItemId: BaseEntity has no id-accepting
        // constructor, so setId() is the only way to assign it here.
        super.setId(id);
    }
}

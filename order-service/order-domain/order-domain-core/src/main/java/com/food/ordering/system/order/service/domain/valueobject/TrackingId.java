package com.food.ordering.system.order.service.domain.valueobject;

import java.util.UUID;

import com.food.ordering.system.domain.entity.BaseEntity;


public class TrackingId extends BaseEntity<UUID> {
    public TrackingId(UUID id) {
        super(id);
    }
}

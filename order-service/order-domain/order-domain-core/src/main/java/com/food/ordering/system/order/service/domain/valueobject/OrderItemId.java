package com.food.ordering.system.order.service.domain.valueobject;

import com.food.ordering.system.domain.entity.BaseEntity;

public class OrderItemId extends BaseEntity<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}

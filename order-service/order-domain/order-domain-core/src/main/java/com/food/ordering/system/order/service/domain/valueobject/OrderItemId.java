package com.food.ordering.system.order.service.domain.valueobject;

import com.food.ordering.system.domain.entity.BaseEntity;

public class OrderItemId extends BaseEntity<Long> {
    public OrderItemId(Long value) {
        // BaseEntity only has a no-arg constructor + setId(); there is no
        // BaseEntity(id) constructor to call with super(value), so the id
        // is assigned through the inherited setter instead.
        super.setId(value);
    }
}

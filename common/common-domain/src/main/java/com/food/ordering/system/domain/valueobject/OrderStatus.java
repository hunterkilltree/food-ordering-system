package com.food.ordering.system.domain.valueobject;

// Order lifecycle states; transitions are enforced by Order itself.
public enum OrderStatus {
    PENDING, PAID, APPROVED, CANCELLED, CANCELLING
}

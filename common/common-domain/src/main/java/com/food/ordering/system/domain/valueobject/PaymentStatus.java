package com.food.ordering.system.domain.valueobject;

// Outcome carried by a PaymentResponse message from the payment service:
// whether the payment completed, was cancelled, or failed.
public enum PaymentStatus {
    COMPLETED,
    CANCELLED,
    FAILED
}

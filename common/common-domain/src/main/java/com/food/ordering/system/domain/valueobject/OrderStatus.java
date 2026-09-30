package com.food.ordering.system.domain.valueobject;

// An Order's lifecycle states. The *transitions* between them (which ones
// are legal from which state) are enforced by Order itself (pay()/approve()/
// initCancel()/cancel() each check orderStatus before changing it) — this
// enum only lists the possible states, it has no behavior of its own.
public enum OrderStatus {
    PENDING, PAID, APPROVED, CANCELLED, CANCELLING
}

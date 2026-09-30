package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

// order-service's own subclass of the shared DomainException — thrown for
// every business-rule violation in this bounded context (invalid state
// transitions in Order, price mismatches, an unknown customer/restaurant,
// etc.), so callers outside this module can catch just OrderDomainException
// without needing to know its internal causes, or catch the shared
// DomainException type if they want to handle any bounded context's
// domain errors uniformly.
public class OrderDomainException extends DomainException {

    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

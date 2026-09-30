package com.food.ordering.system.domain.exception;

// Root for business-rule violations (unchecked). Each bounded context
// extends this with its own subclass, e.g. OrderDomainException.
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

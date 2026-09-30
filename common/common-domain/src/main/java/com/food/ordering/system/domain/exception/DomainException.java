package com.food.ordering.system.domain.exception;

/**
 * Common root for exceptions that signal a business/domain rule was
 * violated (as opposed to a technical failure like a DB timeout). It's a
 * RuntimeException (unchecked) so domain code — entities, domain services —
 * can throw it without every method up the call stack having to declare
 * `throws`; each bounded context (e.g. order-service) extends this with its
 * own subclass (OrderDomainException) so callers can catch at whatever
 * granularity they need: this base type, or just that context's.
 */
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

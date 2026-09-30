package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

/**
 * DDD Value Object base class for typed ids (CustomerId, OrderId, etc).
 * Wrapping a raw UUID/Long in a dedicated type is what stops, say, a
 * CustomerId from being passed where a RestaurantId is expected — the
 * compiler catches it, a raw UUID parameter wouldn't. Unlike BaseEntity,
 * equals()/hashCode() here compare the wrapped value, not object identity —
 * value objects are defined by what they hold, so two ids wrapping the same
 * UUID are equal regardless of which instance you have. The constructor is
 * protected (not public) because BaseId itself is never instantiated
 * directly, only through a concrete subclass like CustomerId.
 */
public abstract class BaseId<T> {
    private final T value;

    protected BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(value, baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

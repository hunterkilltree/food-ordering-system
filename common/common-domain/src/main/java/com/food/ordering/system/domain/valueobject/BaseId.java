package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

// Value Object base for typed ids (CustomerId, OrderId, ...): a type-safe
// wrapper so a CustomerId can't be passed where a RestaurantId is expected.
// Equality by value, not identity.
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

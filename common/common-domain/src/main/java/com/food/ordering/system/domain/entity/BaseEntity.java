package com.food.ordering.system.domain.entity;

import java.util.Objects;

/**
 * DDD Entity base class: an object defined by its identity (id), not by the
 * values of its other fields. Two entities are equal iff they have the same
 * id, even if every other field differs — that's why equals()/hashCode()
 * below only ever look at id, unlike a Value Object (see BaseId) which
 * compares all its fields. ID is generic so each concrete entity can use
 * whatever id type fits it (a value-object id like OrderId, a raw UUID/Long,
 * etc.) while sharing this one identity-comparison implementation.
 */
public abstract class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

package com.food.ordering.system.domain.entity;

// Marker: an Entity that's also a consistency boundary (e.g. Order for its
// OrderItems). Only aggregate roots are loaded/saved directly; everything
// inside is reached through the root.
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {


}

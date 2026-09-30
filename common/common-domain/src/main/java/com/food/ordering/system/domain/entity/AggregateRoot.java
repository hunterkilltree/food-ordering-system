package com.food.ordering.system.domain.entity;

/**
 * Marks an Entity as an Aggregate Root: the single entry point and
 * consistency boundary for a cluster of related entities/value objects
 * (e.g. Order is the root for its OrderItems). Only aggregate roots are
 * loaded and saved directly by repositories — everything else inside the
 * aggregate is reached and mutated through the root, which is what lets the
 * root enforce its invariants (see Order's pay()/approve()/cancel() state
 * checks) instead of callers mutating child entities behind its back. This
 * class adds no members of its own; it exists purely as a marker type so
 * "is this an aggregate root?" is a compile-time, type-checkable question.
 */
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {


}

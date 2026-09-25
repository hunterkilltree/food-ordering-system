package com.food.ordering.system.order.service.domain.entity;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;

    // update builder, getter, setter
    // id must be RestaurantId
}
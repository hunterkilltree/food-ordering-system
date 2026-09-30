package com.food.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

// The command-side line item inside CreateOrderCommand — distinct from
// entity.OrderItem, the domain-side aggregate member. Same simple name,
// different package/purpose; OrderDataMapper has to fully-qualify one of
// them when both are needed in the same method, since Java can't import
// two types with the same simple name into one file.
@Builder
@Getter
@AllArgsConstructor
public class OrderItem {
    @NonNull
    private final UUID productId;

    @NonNull
    private final Integer quantity;

    @NonNull
    private final BigDecimal price;

    @NonNull
    private final BigDecimal totalPrice;
}

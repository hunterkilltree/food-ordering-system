package com.food.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

// Command-side line item — distinct from entity.OrderItem (same simple
// name, different package); OrderDataMapper fully-qualifies one of them.
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

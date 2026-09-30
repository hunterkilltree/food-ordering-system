package com.food.ordering.system.order.service.domain.dto.create;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

// Output Response for "create order". orderId is actually the order's
// TrackingId value, not its internal OrderId (which stays internal).
@Builder
@Getter
@AllArgsConstructor
public class CreateOrderResponse {

    @NonNull
    private final UUID orderId;

    @NonNull
    private final OrderStatus orderStatus;

    @NonNull
    private final String message;
}

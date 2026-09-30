package com.food.ordering.system.order.service.domain.dto.create;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

// The output Response for "create order". orderId here is actually the
// order's TrackingId value (see OrderDataMapper.orderToCreateOrderResponse)
// rather than its internal OrderId — callers track/reference an order by
// trackingId, so that's what's surfaced across the application boundary;
// the aggregate's own OrderId stays internal.
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

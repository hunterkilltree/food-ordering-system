package com.food.ordering.system.order.service.domain.dto.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

// The input Query for the "track order" use case (read-only, as opposed to
// CreateOrderCommand which changes state — see its comment for the
// Command/Query naming). Callers look an order up by its TrackingId value,
// not its internal OrderId, matching what CreateOrderResponse hands back.
@Builder
@Getter
@AllArgsConstructor
public class TrackOrderQuery {
    @NonNull
    private final UUID orderTrackingId;

}

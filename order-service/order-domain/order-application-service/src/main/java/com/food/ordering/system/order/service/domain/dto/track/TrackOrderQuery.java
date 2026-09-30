package com.food.ordering.system.order.service.domain.dto.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

// Input Query for "track order" (read-only). Looks up by TrackingId value,
// matching what CreateOrderResponse hands back.
@Builder
@Getter
@AllArgsConstructor
public class TrackOrderQuery {
    @NonNull
    private final UUID orderTrackingId;

}

package com.food.ordering.system.order.service.domain.dto.track;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

// failureMessages has no @NonNull, unlike orderTrackingId/orderStatus
// above: it's legitimately null/empty for an order that hasn't failed —
// there's nothing to require here, unlike the other two fields which must
// always be present to describe an order's current state.
@Builder
@Getter
@AllArgsConstructor
public class TrackOrderResponse {

    @NonNull
    private final UUID orderTrackingId;

    @NonNull
    private final OrderStatus orderStatus;

    private final List<String> failureMessages;
}

package com.food.ordering.system.order.service.domain.dto.message;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

// Inbound message from the restaurant service (consumed by
// RestaurantApprovalResponseMessageListener) — see PaymentResponse's
// comment for why this has no @NonNull and uses plain String ids.
@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
    private final String id;
    private final String sagaId;
    private final String restaurantId;
    private final Instant createdAt;
    private final OrderApprovalStatus orderApprovalStatus;
    private final List<String> failureMessages;
}

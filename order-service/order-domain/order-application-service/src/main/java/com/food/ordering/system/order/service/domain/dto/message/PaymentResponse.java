package com.food.ordering.system.order.service.domain.dto.message;

import com.food.ordering.system.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

// Represents an inbound message from the payment service (consumed by
// PaymentResponseMessageListener), not an outbound command — so unlike
// CreateOrderCommand/OrderAddress there's no @NonNull here: a message
// deserialized off a Kafka topic isn't something this service controls
// the shape of, and ids are plain Strings (not CustomerId/OrderId value
// objects) because that's the natural shape of a cross-service message
// schema; whoever handles the message parses/converts what it needs.
@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
    private final String id;
    private final String sagaId;
    private final String orderId;
    private final String customerId;
    private final String paymentId;
    private final BigDecimal price;
    private final Instant createdAt;
    private final PaymentStatus paymentStatus;
    private final List<String> failureMessages;
}

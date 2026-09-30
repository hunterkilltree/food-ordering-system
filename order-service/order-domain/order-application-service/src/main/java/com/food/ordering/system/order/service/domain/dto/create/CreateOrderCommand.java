package com.food.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

// The input Command for the "create order" use case (CQRS-style naming:
// Command = an intent to change state, as opposed to a Query like
// TrackOrderQuery). It's a plain DTO of primitives/UUIDs rather than
// domain value objects (CustomerId, Money, ...) on purpose: this is the
// application layer's boundary type, built from whatever a REST
// controller receives off the wire — OrderDataMapper is what translates
// it into real domain types (new CustomerId(...), new Money(...), etc.)
// so the wire format never leaks into the domain model. @NonNull (Lombok)
// makes the generated constructor throw NullPointerException immediately
// if a required field is missing — a fast fail at construction time,
// distinct from the field-level @Max/@Size bean-validation annotations
// used in OrderAddress, which are only enforced where @Valid is applied
// (see OrderApplicationService).
@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    @NonNull
    private final UUID customerId;
    @NonNull
    private final UUID restaurantId;
    @NonNull
    private final BigDecimal price;
    @NonNull
    private final List<OrderItem> items;
    @NonNull
    private final OrderAddress address;
}

package com.food.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Max;

// Note: @Max is a numeric-range constraint (checks a number's value is
// <= the given max); applied to a String field like this it's a no-op —
// Bean Validation silently ignores constraints that don't apply to the
// annotated type. A length limit on these Strings would need @Size(max=...)
// instead. Left as-is here (fixing it wasn't asked for) but worth knowing
// this isn't actually enforcing anything today.
@Builder
@Getter
@AllArgsConstructor
public class OrderAddress {
    @NonNull
    @Max(value = 50)
    private final String street;
    @NonNull
    @Max(value = 10)
    private final String postalCode;
    @NonNull
    @Max(value = 50)
    private final String country;
}

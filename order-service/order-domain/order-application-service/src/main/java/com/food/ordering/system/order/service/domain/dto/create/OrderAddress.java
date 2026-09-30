package com.food.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Max;

// Note: @Max is a numeric constraint; on a String it's a silent no-op —
// a length limit here would need @Size(max=...) instead.
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

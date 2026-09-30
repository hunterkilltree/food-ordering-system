package com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurant;

import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;

// Input port for messages from the restaurant service — see
// PaymentResponseMessageListener's comment for the full rationale.
public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}

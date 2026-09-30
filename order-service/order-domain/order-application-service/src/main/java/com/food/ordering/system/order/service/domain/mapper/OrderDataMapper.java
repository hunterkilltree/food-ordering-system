package com.food.ordering.system.order.service.domain.mapper;

import org.springframework.stereotype.Component;

@Component
// is this class related to factory pattern?
public class OrderDataMapper {

    Restaurant CreateOrderCommandtoRestaurant(CreateOrderCommand createOrderCommand) {
        /* TODO: using builder
        retrun Restaurant.builder()
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId())
        .products(createOrderCommand.getItems each orderItem new ProductId(orderItem.getProductId()))
        */
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        /* TODO:
        return Order builder customerId(new CustomerId(createOrderCommand.getCustomerId()))
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
        .price(new Money(createOrderCommand.getPrice()))
        .items(orderItemsToOrderItemEnttities(createOrderCommand.getItems))
        .build
        */
    }

    //TODO: public CreateOrderResponse orderToCreateOrderResponse(Order order)
    // return CreateOrderResponse.builder()
    // setTrackingId(order.getTrackingId().getValue())
    // orderSTatus(order.getOrderStatus())
    // .build();

    private List<OrderItem> orderItemsToOrderItemEnttities(List<OrderItem> items) {
        // TODO:
        // return orderItems.steam().map(orderItem -> OrderItem builder product (new Product(new ProductId(orderItem.getProduct()))))
        // price(new Money(orderItem.getPrice()))
        // .quantity(orderItem.getQuantity())
        // .subTotal(new Money(orderItem.getSubTotal()))
        // .build.collect(Collectors.toList())

    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        // TODO: return new StreetAddress(UUID.randomUIID(), orderAddress.getStreet(), orderAddress.getPostalCode(), orderAddress.getCity())
    }
}

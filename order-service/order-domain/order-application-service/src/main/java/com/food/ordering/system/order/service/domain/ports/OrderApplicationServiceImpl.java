package com.food.ordering.system.order.service.domain.ports;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated // enable valid annotation in interface
@Service
// Application Service is the first contract point to the outside in DDD:
// this is that entry point's implementation, and its whole job below is to
// forward each call to a handler, which forwards to the domain service and
// entities to actually complete the business logic — createOrder/trackOrder
// here contain no business rules themselves, just delegation.
//
// Package-private on purpose, not public: nothing outside this module ever
// references OrderApplicationServiceImpl directly — callers (e.g. a REST
// controller in order-application) depend only on the OrderApplicationService
// interface. Spring can still instantiate and wire a package-private
// @Service via component scanning/reflection, so there's no functional
// need to widen the type's visibility beyond this package.
// (Note: the interface itself lives one package down, in ports.input.service
// — they aren't literally the same package, but nothing here needs the impl
// type to be visible there either, since the interface is what's exported.)
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                 OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}

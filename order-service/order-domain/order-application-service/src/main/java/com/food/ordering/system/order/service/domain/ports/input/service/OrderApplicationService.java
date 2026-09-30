package com.food.ordering.system.order.service.domain.ports.input.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

// Application Service is the first contract point to the outside in DDD:
// it's the boundary a driving adapter (e.g. a REST controller in
// order-application, not written yet) calls into, and it forwards that
// call to the domain service and entities to complete the actual business
// logic (see OrderApplicationServiceImpl -> OrderCreateCommandHandler/
// OrderTrackCommandHandler -> OrderDomainService -> Order). It holds no
// business rules of its own — it orchestrates.
//
// Primary input port: the single use-case boundary a driving adapter
// depends on. It's an interface — implemented package-privately by
// OrderApplicationServiceImpl — so that boundary is a stable, mockable
// contract independent of how the use cases are actually orchestrated.
// @Valid here (combined with @Validated on the implementation class)
// triggers Bean Validation on the command/query's own annotated fields
// (e.g. CreateOrderCommand's @NonNull ones, OrderAddress's — currently
// no-op — @Max ones) automatically whenever createOrder/trackOrder is
// called through a Spring-managed proxy, rather than each handler having
// to validate its input by hand.
public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}

package com.food.ordering.system.order.service.domain.ports.input.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

// Primary input port: the single use-case boundary a driving adapter
// (e.g. a REST controller in order-application, not written yet) depends
// on. It's an interface — implemented package-privately by
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

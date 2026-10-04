package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// PER_CLASS: reuses one test instance for the whole class, so @BeforeAll/@AfterAll
// can be instance methods instead of static — needed to touch instance fields.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
class OrderApplicationServiceTest {

    @Autowired
    private OrderApplicationService orderApplicationService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderDataMapper orderDataMapper;

    private CreateOrderCommand createOrderCommand;
    private CreateOrderCommand createOrderCommandWrongPrice;
    private CreateOrderCommand createOrderCommandWrongProductPrice;

    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID RESTAURANT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final UUID ORDER_ID = UUID.fromString("15a497c1-0f4b-4eb4-9ae8-73e45ab3cb3c");
    private final BigDecimal PRICE = new BigDecimal("100.00");

    @BeforeEach
    void init() {
        createOrderCommand = createOrderCommand(RESTAURANT_ID, CUSTOMER_ID, PRODUCT_ID, PRICE,
                new BigDecimal("50.00"), new BigDecimal("50.00"));
        createOrderCommandWrongPrice = createOrderCommand(RESTAURANT_ID, CUSTOMER_ID, PRODUCT_ID,
                new BigDecimal("250.00"), new BigDecimal("50.00"), new BigDecimal("50.00"));
        createOrderCommandWrongProductPrice = createOrderCommand(RESTAURANT_ID, CUSTOMER_ID, PRODUCT_ID, PRICE,
                new BigDecimal("60.00"), new BigDecimal("50.00"));

        Customer customer = new Customer(new CustomerId(CUSTOMER_ID));

        Restaurant restaurantResponse = Restaurant.builder()
                .restaurantId(new RestaurantId(RESTAURANT_ID))
                .products(List.of(
                        new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00")))))
                .active(true)
                .build();

        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        order.setId(new OrderId(ORDER_ID));

        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
                .thenReturn(Optional.of(restaurantResponse));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
    }

    @Test
    void testCreateOrder() {
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        assertEquals(OrderStatus.PENDING, createOrderResponse.getOrderStatus());
        assertEquals("Order Created Successfully", createOrderResponse.getMessage());
        // TODO: check trackId not null
    }

    @Test
    void testCreateOrderWithWrongTotalPrice() {
        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
        assertEquals("Total price: 250.00 is not equal to 100.00", orderDomainException.getMessage());
    }

    @Test
    void testCreateOrderWithWrongProductPrice() {
        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
        assertEquals("Order item price: 60.00 is not valid for product: " + PRODUCT_ID,
                orderDomainException.getMessage());
    }

    @Test
    void testCreateOrderWithNonExistingCustomer() {
        UUID unknownCustomerId = UUID.randomUUID();
        CreateOrderCommand command = createOrderCommand(RESTAURANT_ID, unknownCustomerId, PRODUCT_ID, PRICE,
                new BigDecimal("50.00"), new BigDecimal("50.00"));

        when(customerRepository.findCustomer(unknownCustomerId)).thenReturn(Optional.empty());

        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(command));
        assertEquals("Could not find customer with customer id: " + unknownCustomerId,
                orderDomainException.getMessage());
    }

    private CreateOrderCommand createOrderCommand(UUID restaurantId, UUID customerId, UUID productId,
                                                    BigDecimal price, BigDecimal subTotal1, BigDecimal subTotal2) {
        return CreateOrderCommand.builder()
                .customerId(customerId)
                .restaurantId(restaurantId)
                .price(price)
                .items(List.of(
                        com.food.ordering.system.order.service.domain.dto.create.OrderItem.builder()
                                .productId(productId)
                                .quantity(1)
                                .price(subTotal1)
                                .totalPrice(subTotal1)
                                .build(),
                        com.food.ordering.system.order.service.domain.dto.create.OrderItem.builder()
                                .productId(productId)
                                .quantity(1)
                                .price(subTotal2)
                                .totalPrice(subTotal2)
                                .build()))
                .address(OrderAddress.builder()
                        .street("street-1")
                        .postalCode("1234")
                        .country("US")
                        .build())
                .build();
    }
}

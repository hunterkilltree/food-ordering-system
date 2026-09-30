
@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderDomainrderDomainSerivce ...
    private final OrderRepository ... 
    private final CustomerRepository ...
    private final RestaurantRepository ...

    private final OrderDataMapper ....


    //TODO:  create Constructor

    //TODO: @Transactional CreateOrderResponse createOrder(CreateOrderCommand)
    // checkCusomter(createOrderCommand.getCustomerId())
    // restaunrant = checkRestaurant(createOrderCommand)
    // order = orderDataMapper.createOrderCommandToOrder()
    // orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
    // orderResult = saveOrder(orderCreatedEvent)
    // log.info("......", orderResult.getId().getValue())
    // return orderDataMapper.orderToCreateOrderResponse

    // TODO: private void checkCusomter(UUID customierId)
    // Optional<Customer> customer = customerRepository.findCustomer(customerId)
    // if (emtpy()) => log.warn("Could not find ...")
    // throw new OrderDomainException()

    // TODO: private Restaurant checkRestaurant(CreateOrderCommand)
    // Restaurant restaurant = orderDataMapper.createOrderCommandToRetaurant(createOrderCommand)
    // Optional optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant)
    // if (optionalRestaurant is empty)
    // log warn ("Could not find ") throw new OrderDomainException("...")\

    // return optionalRestaurant.get();


    //TODO: private Order saveOrder(order)
    // orderResult = orderRespository.save(order)
    // if orderResult == null then 
    // log error(....)
    // throw new OrderDomainException("...")
    // log info(".... with id {}");


}
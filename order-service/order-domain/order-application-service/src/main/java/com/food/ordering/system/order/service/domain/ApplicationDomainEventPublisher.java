

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements
    ApplicationEventPublisherAware,
    DomainEventPublisher<OrderCreatedEvent> {


    private ApplicationEventPublisher ...


    // TODO:
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher
    }

    // TODO:
    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info(".....", domainEvent.getOrder().getId().getValue());
        

    }

}
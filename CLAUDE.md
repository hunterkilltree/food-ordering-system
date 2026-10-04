# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Multi-module Maven project implementing a food ordering system using Clean Architecture, Hexagonal
(Ports & Adapters) Architecture, and Domain-Driven Design. Currently only `order-service` is built out;
other microservices (payment, restaurant, customer) implied by the domain are not yet present.

Java 21, Spring Boot 2.6.7 (`spring-boot-starter-parent`), Lombok 1.18.32 (pinned above the Spring Boot
BOM's managed version because the older Lombok can't run on JDK 21's javac).

## Build & test commands

Run from the repo root (reactor build covers all modules):

```
mvn clean install
mvn test
mvn -pl order-service/order-domain/order-application-service -am test   # single module + its dependencies
mvn -Dtest=OrderDomainServiceImplTest test                               # single test class
```

Generate the module dependency graph (useful when reasoning about allowed dependency directions):

```
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"
```

## Module structure and dependency direction

```
food-ordering-system (root pom, dependencyManagement for all internal artifacts)
├── common
│   └── common-domain          # shared Entity/AggregateRoot/ValueObject/DomainEvent base types
└── order-service
    ├── order-domain
    │   ├── order-domain-core        # Order aggregate, domain services, value objects — depends only on common-domain
    │   └── order-application-service # use cases, ports (input/output), DTOs, mappers — depends on order-domain-core
    ├── order-application         # (scaffolded, not yet implemented) REST adapter
    ├── order-dataaccess           # (scaffolded, not yet implemented) JPA adapter implementing output repository ports
    ├── order-messaging            # (scaffolded, not yet implemented) Kafka adapter implementing message publisher/listener ports
    └── order-container            # (scaffolded, not yet implemented) Spring Boot application assembly module
```

Dependencies only point inward: `order-domain-core` depends on nothing in this repo but `common-domain`.
`order-application-service` depends on `order-domain-core` but not on any adapter module. Adapter modules
(`order-dataaccess`, `order-messaging`, `order-application`) are meant to implement the ports declared in
`order-application-service` and depend on it — never the other way around. `order-container` is the
composition root that wires concrete adapters to the application service.

When adding a new internal artifact, register its version in the root `pom.xml`'s `<dependencyManagement>`
so child modules can omit the `<version>`.

## Architecture within order-domain-core / order-application-service

- **Aggregate root**: `Order` (`order-domain-core/.../entity/Order.java`) is the only entity loaded/saved
  directly. It's built via a `Builder` (covers both "new order" and "rehydrated from persistence" cases)
  and exposes state transitions (`pay()`, `approve()`, `initCancel()`, `cancel()`) as the *only* way
  `orderStatus` changes — each checks the current state before transitioning and throws
  `OrderDomainException` otherwise. Other entities inside the aggregate (`OrderItem`, `Product`) are
  reached only through `Order`.
- **Value objects**: identity types (`OrderId`, `CustomerId`, etc.) extend common `BaseId<T>`; domain
  value objects like `Money`, `StreetAddress`, `TrackingId` live in `order-domain-core/.../valueobject`.
- **Domain service** (`OrderDomainService`/`Impl`): stateless domain logic that doesn't belong on the
  aggregate itself (e.g. validating an order against restaurant data) and emits domain events.
- **Application service layer** (`order-application-service`) is DDD's outside-facing contract:
  - `ports/input/service/OrderApplicationService` — the use-case API adapters call into.
  - `ports/input/message/listener/*` — inbound message contracts (e.g. `PaymentResponseMessageListener`).
  - `ports/output/repository/*` — persistence contracts (`OrderRepository`, `CustomerRepository`,
    `RestaurantRepository`) that `order-dataaccess` will implement.
  - `ports/output/message/publisher/*` — outbound messaging contracts that `order-messaging` will
    implement, split by downstream concern (payment, restaurant approval).
  - `*CommandHandler` classes (`OrderCreateCommandHandler`, `OrderTrackCommandHandler`) orchestrate a
    single use case and are the entry point from `OrderApplicationServiceImpl`.
  - `OrderCreateHelper` carries the `@Transactional` boundary for order creation and is kept separate
    from `OrderCreateCommandHandler` specifically so the resulting domain event is published only after
    that transaction commits.
  - `mapper/OrderDataMapper` converts between DTOs (`dto/create`, `dto/track`, `dto/message`) and domain
    entities/value objects — DTOs never leak into `order-domain-core`.

## Notes

- `common-domain`'s `AggregateRoot<ID>` is just a marker subclass of `BaseEntity<ID>` denoting a
  consistency boundary; don't add behavior to it that belongs on a specific aggregate.
- Entity getters in aggregates are intentionally narrow — only fields the application layer actually
  reads are exposed (see `Order`'s getters); don't widen them without a concrete caller need.

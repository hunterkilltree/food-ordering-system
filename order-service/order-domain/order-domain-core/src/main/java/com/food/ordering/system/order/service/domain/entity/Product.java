package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

// Entity (extends BaseEntity, not AggregateRoot) because a Product only
// exists as part of a Restaurant's catalog — nothing loads or saves a
// Product on its own. Its inherited equals()/hashCode() compare only
// ProductId (BaseEntity's identity semantics — see its Javadoc), which is
// exactly what lets OrderDomainServiceImpl match an order's id-only
// Product reference to the restaurant's real Product by id, even though
// name/price differ between the two instances until confirmed.
public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    // Id-only constructor: used to build an unconfirmed product reference
    // from a client command (only the id is known yet). name/price stay
    // null until updateWithConfirmedNameAndPrice() fills them in from the
    // restaurant's actual catalog. This replaces an earlier version of
    // this constructor that was typed Product(Product) — a self-referential
    // parameter that could never have compiled as an id-only constructor.
    public Product(ProductId productId) {
        super.setId(productId);
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}

package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Value Object wrapping a monetary amount. Money exists instead of passing
 * BigDecimal around directly so every arithmetic operation (add/subtract/
 * multiply) goes through the same rounding rule in one place — setScale()
 * below fixes every result to 2 decimal places using HALF_EVEN ("banker's
 * rounding": ties round to the nearest even digit), which avoids the slight
 * upward bias plain HALF_UP rounding introduces over many transactions.
 * Immutable: every operation returns a new Money rather than mutating this
 * one, so a Money value can be shared/compared safely without defensive
 * copying.
 */
public class Money {
    private final BigDecimal amount;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isGreaterThanZero() {
        // fact: don't use == because 0.00 return false
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.getAmount())));
    }

    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    private BigDecimal setScale(BigDecimal amount) {
        // fact: round toward to nearest neighbor. If both neighbors are equidistant, round towards the even neighbor.
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}

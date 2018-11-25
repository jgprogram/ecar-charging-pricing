package com.jgprogram.common.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money extends ValueObject {

    private BigDecimal value;
    private String currency;

    public Money(Double value, String currency) {
        this.value = BigDecimal.valueOf(value);
        this.currency = currency;
    }

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money add(Money addend) {
        assertArgumentEquals(this.currency, addend.currency,
                "Currency must equals.");

        return new Money(value.add(addend.value), currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return round(value).equals(round(money.value)) &&
                currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }

    private static BigDecimal round(BigDecimal in) {
        return in.setScale(2, RoundingMode.CEILING);
    }
}

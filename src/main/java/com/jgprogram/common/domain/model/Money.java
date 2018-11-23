package com.jgprogram.common.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Money extends ValueObject {

    private BigDecimal value;
    private String currency;

    public Money(Double value, String currency) {
        this.value = BigDecimal.valueOf(value);
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value.equals(money.value) &&
                currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}

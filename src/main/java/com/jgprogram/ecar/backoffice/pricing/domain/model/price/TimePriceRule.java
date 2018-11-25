package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.common.domain.model.ValueObject;

import java.time.LocalTime;
import java.util.Objects;

public class TimePriceRule extends ValueObject {

    private LocalTime start;
    private LocalTime end;
    private Money price;

    public TimePriceRule(LocalTime start, LocalTime end, Money price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePriceRule that = (TimePriceRule) o;
        return start.equals(that.start) &&
                end.equals(that.end) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, price);
    }

    public Money price() {
        return price;
    }

    public LocalTime start() {
        return start;
    }

    public LocalTime end() {
        return end;
    }
}

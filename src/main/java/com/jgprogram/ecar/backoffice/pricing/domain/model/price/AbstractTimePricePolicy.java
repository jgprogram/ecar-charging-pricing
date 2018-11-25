package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.DomainPolicy;
import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractTimePricePolicy extends DomainPolicy implements PricePolicy {

    private List<TimePriceRule> rules;

    protected AbstractTimePricePolicy(Collection<TimePriceRule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    @Override
    public Money apply(ChargingTime chargingTime) {
        return Stream.iterate(chargingTime.start(), t -> t.plusMinutes(1))
                .limit(chargingTime.durationInMinutes() + 1)
                .map(this::applyRule)
                .reduce(new Money(0D, "EUR"), Money::add);
    }

    Collection<TimePriceRule> rules() {
        return Collections.unmodifiableList(rules);
    }

    private boolean isTimeInRule(LocalTime time, TimePriceRule r) {
        return (r.start().isBefore(time) || r.start().equals(time))
                && (r.end().isAfter(time) || r.end().equals(time));
    }

    private Money applyRule(LocalDateTime forDataTime) {
        final LocalTime timeToCalc = forDataTime.toLocalTime();
        Money price = rules.stream()
                .filter(r -> isTimeInRule(timeToCalc, r))
                .findAny()
                .map(TimePriceRule::price)
                .orElse(new Money(0D, "EUR"));

        return price;
    }
}

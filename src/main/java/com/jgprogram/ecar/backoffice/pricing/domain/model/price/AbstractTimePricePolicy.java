package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.DomainPolicy;
import com.jgprogram.common.domain.model.Money;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTimePricePolicy extends DomainPolicy implements PricePolicy {

    private List<TimePriceRule> rules;

    protected AbstractTimePricePolicy(Collection<TimePriceRule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    Collection<TimePriceRule> rules() {
        return Collections.unmodifiableList(rules);
    }

    @Override
    public Money apply(LocalDateTime forDataTime) {
        final LocalTime timeToCalc = forDataTime.toLocalTime();
        Money price = rules.stream()
                .filter(r -> isTimeInRule(timeToCalc, r))
                .findAny()
                .map(TimePriceRule::price)
                .orElse(new Money(0D, "EUR"));

        return price;
    }

    private boolean isTimeInRule(LocalTime time, TimePriceRule r) {
        return (r.start().isBefore(time) || r.start().equals(time))
                && (r.end().isAfter(time) || r.end().equals(time));
    }
}

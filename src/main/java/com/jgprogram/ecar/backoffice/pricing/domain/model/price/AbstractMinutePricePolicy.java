package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.DomainPolicy;
import com.jgprogram.common.domain.model.Money;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractMinutePricePolicy extends DomainPolicy implements PricePolicy {

    private List<MinutePriceRule> rules;

    protected AbstractMinutePricePolicy(Collection<MinutePriceRule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    Collection<MinutePriceRule> rules() {
        return Collections.unmodifiableList(rules);
    }

    @Override
    public Money apply(LocalDateTime forDataTime) {
        final LocalTime timeToCalc = forDataTime.toLocalTime();
        Money price = rules.stream()
                .filter(r -> isTimeInRule(timeToCalc, r))
                .findAny()
                .map(MinutePriceRule::price)
                .orElse(new Money(0D, "EUR"));

        return price;
    }

    private boolean isTimeInRule(LocalTime time, MinutePriceRule r) {
        return (r.start().isBefore(time) || r.start().equals(time))
                && (r.end().isAfter(time) || r.end().equals(time));
    }
}

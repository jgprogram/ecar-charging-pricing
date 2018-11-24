package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.Money;

import java.time.LocalTime;
import java.util.*;

public class DefaultPricePolicy extends AbstractMinutePricePolicy {

    public DefaultPricePolicy() {
        super(Arrays.asList(
                new MinutePriceRule(
                        LocalTime.of(0, 0),
                        LocalTime.of(11, 59),
                        new Money(0.05, "EUR")),
                new MinutePriceRule(
                        LocalTime.of(12, 0),
                        LocalTime.of(23, 59),
                        new Money(0.06, "EUR"))
        ));
    }
}

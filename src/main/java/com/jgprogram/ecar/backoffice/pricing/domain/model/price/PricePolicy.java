package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.Money;

import java.time.LocalDateTime;

public interface PricePolicy {
    Money apply(LocalDateTime forDataTime);
}

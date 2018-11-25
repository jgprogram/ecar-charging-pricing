package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime;

import java.time.LocalDateTime;

public interface PricePolicy {
    Money apply(ChargingTime chargingTime);
}

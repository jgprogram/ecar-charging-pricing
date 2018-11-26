package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime;

public interface PricePolicy {
    Money apply(ChargingTime chargingTime);
}

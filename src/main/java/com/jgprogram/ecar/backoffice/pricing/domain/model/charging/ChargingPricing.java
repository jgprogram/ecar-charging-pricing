package com.jgprogram.ecar.backoffice.pricing.domain.model.charging;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.common.domain.model.ValueObject;
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;

public class ChargingPricing extends ValueObject {
    private ChargingPricingId chargingPricingId;
    private ChargingTime chargingTime;
    private CustomerId customerId;
    private Money totalPrice;

    public ChargingPricing(ChargingPricingId chargingPricingId, ChargingTime chargingTime, CustomerId customerId, Money totalPrice) {
        this.chargingPricingId = chargingPricingId;
        this.chargingTime = chargingTime;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }

    public ChargingPricingId chargingPricingId() {
        return chargingPricingId;
    }

    public ChargingTime chargingTime() {
        return chargingTime;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public Money totalPrice() {
        return totalPrice;
    }
}


package com.jgprogram.ecar.backoffice.pricing.domain.model.charging;

import com.jgprogram.common.domain.model.AggregateRoot;
import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;

public class ChargingPricing extends AggregateRoot<ChargingPricingId> {
    private ChargingTime chargingTime;
    private CustomerId customerId;
    private Money totalPrice;

    public ChargingPricing(ChargingPricingId chargingPricingId, ChargingTime chargingTime, CustomerId customerId, Money totalPrice) {
        super(chargingPricingId);
        this.chargingTime = chargingTime;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
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


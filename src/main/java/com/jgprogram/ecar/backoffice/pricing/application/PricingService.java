package com.jgprogram.ecar.backoffice.pricing.application;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PricingService {

    private final PricePolicyFactory pricePolicyFactory;

    @Autowired
    public PricingService(PricePolicyFactory pricePolicyFactory) {
        this.pricePolicyFactory = pricePolicyFactory;
    }

    public ChargingPricing calculate(ChargingPricingRequest request) {
        CustomerId customerId = new CustomerId(request.getCustomerId());
        ChargingTime chargingTime = new ChargingTime(request.getStartCharging(), request.getStopCharging());

        return new ChargingPricing(
                new ChargingPricingId(UUID.randomUUID().toString()),
                chargingTime,
                customerId,
                new Money(0D, "EUR")
        );
    }
}
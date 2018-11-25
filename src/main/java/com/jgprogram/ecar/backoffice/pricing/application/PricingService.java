package com.jgprogram.ecar.backoffice.pricing.application;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicyFactory;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory;
import com.jgprogram.ecar.backoffice.pricing.shared.ChargingPricingData;
import com.jgprogram.ecar.backoffice.pricing.shared.ChargingPricingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class PricingService {

    private final PricePolicyFactory pricePolicyFactory;
    private final DiscountPolicyFactory discountPolicyFactory;

    @Autowired
    public PricingService(PricePolicyFactory pricePolicyFactory,
                          DiscountPolicyFactory discountPolicyFactory) {
        this.pricePolicyFactory = pricePolicyFactory;
        this.discountPolicyFactory = discountPolicyFactory;
    }

    public ChargingPricingData calculate(ChargingPricingRequest request) {
        CustomerId customerId = new CustomerId(request.getCustomerId());
        ChargingTime chargingTime = new ChargingTime(request.getStartCharging(), request.getStopCharging());
        PricePolicy pricePolicy = pricePolicyFactory.create();

        Money totalPrice = pricePolicy.apply(chargingTime);

        DiscountPolicy discountPolicy = discountPolicyFactory.createFor(customerId);
        totalPrice = discountPolicy.apply(totalPrice);

        ChargingPricing chargingPricing = new ChargingPricing(
                new ChargingPricingId(UUID.randomUUID().toString()),
                chargingTime,
                customerId,
                totalPrice
        );

        // TODO - repository add charging pricing

        return mapToChargingPricingData(chargingPricing);
    }

    private ChargingPricingData mapToChargingPricingData(ChargingPricing chargingPricing) {
        return new ChargingPricingData(
                chargingPricing.totalPrice().currency(),
                chargingPricing.customerId().id(),
                chargingPricing.chargingPricingId().id(),
                toDate(chargingPricing.chargingTime().start()),
                toDate(chargingPricing.chargingTime().stop()),
                chargingPricing.totalPrice().doubleValue()
        );
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
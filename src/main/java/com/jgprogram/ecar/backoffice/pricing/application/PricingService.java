package com.jgprogram.ecar.backoffice.pricing.application;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingRepository;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicyFactory;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory;
import com.jgprogram.ecar.backoffice.pricing.shared.ChargingPricingData;
import com.jgprogram.ecar.backoffice.pricing.shared.ChargingPricingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PricingService {

    private final PricePolicyFactory pricePolicyFactory;
    private final DiscountPolicyFactory discountPolicyFactory;
    private final ChargingPricingRepository chargingPricingRepository;
    private final ChargingPricingMapper chargingPricingMapper;

    @Autowired
    public PricingService(PricePolicyFactory pricePolicyFactory,
                          DiscountPolicyFactory discountPolicyFactory,
                          ChargingPricingRepository chargingPricingRepository,
                          ChargingPricingMapper chargingPricingMapper) {
        this.pricePolicyFactory = pricePolicyFactory;
        this.discountPolicyFactory = discountPolicyFactory;
        this.chargingPricingRepository = chargingPricingRepository;
        this.chargingPricingMapper = chargingPricingMapper;
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

        chargingPricingRepository.add(chargingPricing);

        return chargingPricingMapper.map(chargingPricing);
    }
}
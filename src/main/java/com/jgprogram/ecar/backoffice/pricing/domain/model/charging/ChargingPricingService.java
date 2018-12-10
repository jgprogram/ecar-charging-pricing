package com.jgprogram.ecar.backoffice.pricing.domain.model.charging;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicyFactory;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory;
import org.springframework.stereotype.Service;

@Service
public class ChargingPricingService {

    private final PricePolicyFactory pricePolicyFactory;
    private final DiscountPolicyFactory discountPolicyFactory;

    public ChargingPricingService() {
        pricePolicyFactory = new PricePolicyFactory();
        discountPolicyFactory = new DiscountPolicyFactory();
    }

    ChargingPricingService(PricePolicyFactory pricePolicyFactory, DiscountPolicyFactory discountPolicyFactory) {
        this.pricePolicyFactory = pricePolicyFactory;
        this.discountPolicyFactory = discountPolicyFactory;
    }

    public Money calculateTotalPrice(CustomerId customerId, ChargingTime chargingTime) {
        PricePolicy pricePolicy = pricePolicyFactory.create();

        Money totalPrice = pricePolicy.apply(chargingTime);

        DiscountPolicy discountPolicy = discountPolicyFactory.createFor(customerId);
        totalPrice = discountPolicy.apply(totalPrice);

        return totalPrice;
    }
}

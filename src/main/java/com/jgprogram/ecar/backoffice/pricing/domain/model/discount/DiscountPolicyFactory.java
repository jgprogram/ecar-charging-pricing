package com.jgprogram.ecar.backoffice.pricing.domain.model.discount;

import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class DiscountPolicyFactory {

    private final VIPDiscountPolicy vipDiscountPolicy;
    private final NoDiscountPolicy noDiscountPolicy;

    public DiscountPolicyFactory() {
        vipDiscountPolicy = new VIPDiscountPolicy();
        noDiscountPolicy = new NoDiscountPolicy();
    }

    public DiscountPolicy createFor(CustomerId customerId) {
        if (customerId != null && "VIP-ID".equals(customerId.id())) {
            return vipDiscountPolicy;
        } else {
            return noDiscountPolicy;
        }
    }
}

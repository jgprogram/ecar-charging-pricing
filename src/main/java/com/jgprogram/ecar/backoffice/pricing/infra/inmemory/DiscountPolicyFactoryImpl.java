package com.jgprogram.ecar.backoffice.pricing.infra.inmemory;

import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicyFactory;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.NoDiscountPolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.VIPDiscountPolicy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"in-memory", "default"})
public class DiscountPolicyFactoryImpl implements DiscountPolicyFactory {

    private final VIPDiscountPolicy vipDiscountPolicy;
    private final NoDiscountPolicy noDiscountPolicy;

    public DiscountPolicyFactoryImpl() {
        vipDiscountPolicy = new VIPDiscountPolicy();
        noDiscountPolicy = new NoDiscountPolicy();
    }

    @Override
    public DiscountPolicy createFor(CustomerId customerId) {
        if (customerId != null && "VIP-ID".equals(customerId.id())) {
            return vipDiscountPolicy;
        } else {
            return noDiscountPolicy;
        }
    }
}

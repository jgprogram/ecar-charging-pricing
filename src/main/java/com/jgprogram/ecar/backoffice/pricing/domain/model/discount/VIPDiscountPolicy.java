package com.jgprogram.ecar.backoffice.pricing.domain.model.discount;

import com.jgprogram.common.domain.model.DomainPolicy;
import com.jgprogram.common.domain.model.Money;

public class VIPDiscountPolicy extends DomainPolicy implements DiscountPolicy {

    @Override
    public Money apply(Money totalPrice) {
        return totalPrice.subtract(totalPrice.multiply(0.1));
    }
}

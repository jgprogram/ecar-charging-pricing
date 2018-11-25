package com.jgprogram.ecar.backoffice.pricing.domain.model.discount;

import com.jgprogram.common.domain.model.Money;

public interface DiscountPolicy {

    Money apply(Money totalPrice);
}

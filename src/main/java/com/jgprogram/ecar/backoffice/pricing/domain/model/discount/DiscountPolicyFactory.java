package com.jgprogram.ecar.backoffice.pricing.domain.model.discount;

import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;

public interface DiscountPolicyFactory {

    DiscountPolicy createFor(CustomerId customerId);
}

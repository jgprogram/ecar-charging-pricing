package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import org.springframework.stereotype.Component;

@Component
public interface PricePolicyFactory {
    PricePolicy create();
}

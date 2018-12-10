package com.jgprogram.ecar.backoffice.pricing.domain.model.price;

import org.springframework.stereotype.Component;

@Component
public class PricePolicyFactory {

    private final DefaultPricePolicy defaultPricePolicy;

    public PricePolicyFactory() {
        defaultPricePolicy = new DefaultPricePolicy();
    }

    public PricePolicy create() {
        return defaultPricePolicy;
    }
}

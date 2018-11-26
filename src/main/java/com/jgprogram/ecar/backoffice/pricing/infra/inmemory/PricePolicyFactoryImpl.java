package com.jgprogram.ecar.backoffice.pricing.infra.inmemory;

import com.jgprogram.ecar.backoffice.pricing.domain.model.price.DefaultPricePolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy;
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"in-memory", "default"})
public class PricePolicyFactoryImpl implements PricePolicyFactory {

    private final DefaultPricePolicy defaultPricePolicy;

    public PricePolicyFactoryImpl() {
        defaultPricePolicy = new DefaultPricePolicy();
    }

    @Override
    public PricePolicy create() {
        return defaultPricePolicy;
    }
}

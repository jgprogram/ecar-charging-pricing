package com.jgprogram.ecar.backoffice.pricing.infra.inmemory;

import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Profile({"in-memory", "default"})
public class ChargingPricingRepositoryImpl implements ChargingPricingRepository {

    private Set<ChargingPricing> chargingPricingSet = new HashSet<>();

    @Override
    public void add(ChargingPricing chargingPricing) {
        chargingPricingSet.add(chargingPricing);
    }
}

package com.jgprogram.ecar.backoffice.pricing.application;

import com.jgprogram.common.domain.model.Money;
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PricingService {

    private final ChargingPricingService chargingPricingService;
    private final ChargingPricingRepository chargingPricingRepository;
    private final ChargingPricingMapper chargingPricingMapper = new ChargingPricingMapper();

    @Autowired
    public PricingService(ChargingPricingService chargingPricingService,
                          ChargingPricingRepository chargingPricingRepository) {
        this.chargingPricingService = chargingPricingService;
        this.chargingPricingRepository = chargingPricingRepository;
    }

    public ChargingPricingData createPricing(ChargingPricingRequest request) {
        CustomerId customerId = new CustomerId(request.getCustomerId());
        ChargingTime chargingTime = new ChargingTime(request.getStartCharging(), request.getStopCharging());
        Money totalPrice = chargingPricingService.calculateTotalPrice(customerId, chargingTime);

        ChargingPricing chargingPricing = new ChargingPricing(
                new ChargingPricingId(UUID.randomUUID().toString()),
                chargingTime,
                customerId,
                totalPrice
        );

        chargingPricingRepository.add(chargingPricing);

        return chargingPricingMapper.map(chargingPricing);
    }
}
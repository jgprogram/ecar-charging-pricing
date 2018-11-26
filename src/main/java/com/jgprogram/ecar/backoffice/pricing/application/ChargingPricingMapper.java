package com.jgprogram.ecar.backoffice.pricing.application;

import com.jgprogram.common.utils.Mapper;
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing;
import com.jgprogram.ecar.backoffice.pricing.shared.ChargingPricingData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
class ChargingPricingMapper implements Mapper<ChargingPricing, ChargingPricingData> {

    @Override
    public ChargingPricingData map(ChargingPricing source) {
        return new ChargingPricingData(
                source.totalPrice().currency(),
                source.customerId().id(),
                source.aggregateId().id(),
                toDate(source.chargingTime().start()),
                toDate(source.chargingTime().stop()),
                source.totalPrice().doubleValue()
        );
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

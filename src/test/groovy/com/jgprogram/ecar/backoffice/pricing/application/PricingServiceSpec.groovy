package com.jgprogram.ecar.backoffice.pricing.application

import com.jgprogram.common.domain.model.Money
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory

import spock.lang.*

import java.time.LocalDateTime

class PricingServiceSpec extends Specification {

    PricingService pricingService
    def eur_0 = new Money(0, "EUR")

    def setup() {
        PricePolicy pricePolicy = Mock()
        pricePolicy.apply(_ as ChargingTime) >> eur_0
        PricePolicyFactory pricePolicyFactory = Mock()
        pricePolicyFactory.create() >> pricePolicy
        pricingService = new PricingService(pricePolicyFactory)
    }

    def "should calculate charging pricing request"() {
        given:
        LocalDateTime startCharging
        LocalDateTime stopCharging
        String customerId = "testCustomerId"

        when:
        ChargingPricing pricing = pricingService.calculate(new ChargingPricingRequest(
                customerId,
                startCharging,
                stopCharging,
        ))

        then:
        pricing.chargingPricingId() != null
        pricing.chargingTime().start() == startCharging
        pricing.chargingTime().stop() == stopCharging
        pricing.customerId() == new CustomerId(customerId)
        pricing.totalPrice() == eur_0
    }
}
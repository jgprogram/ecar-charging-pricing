package com.jgprogram.ecar.backoffice.pricing.application

import spock.lang.Specification

import java.time.LocalDateTime

class PricingServiceSpec extends Specification {

    def pricingService = new PricingService()

    def "should calculate charging pricing request"(startCharging, stopCharging, pricingValue) {
        given:
        def chargingPricingRequest = new ChargingPricingRequest(
                new CustomerId("testCustomerId"),
                new ChargingTime(
                        startCharging,
                        stopCharging,
                )
        )

        when:
        Pricing pricing = pricingService.calculate(chargingPricingRequest)

        then:
        pricing.id() != null
        pricing.value() == pricingValue

        where:
        startCharging        | stopCharging      | pricingValue
        LocalDateTime.of(2018, 1, 1, 9, 11)
    }
}

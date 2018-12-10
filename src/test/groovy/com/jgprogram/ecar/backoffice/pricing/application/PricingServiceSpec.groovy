package com.jgprogram.ecar.backoffice.pricing.application

import com.jgprogram.common.domain.model.Money
import com.jgprogram.common.exception.BusinessLogicException
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingRepository
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingService
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime

import spock.lang.*

class PricingServiceSpec extends Specification implements SpecHelper {

    static _0_EUR = new Money(0, "EUR")
    static _2018_11_26_18_00 = new Date(1543255200000)
    static _2018_11_26_19_00 = new Date(1543258800000)


    ChargingPricingService chargingPricingService = Mock()
    PricingService pricingService
    ChargingPricingRepository chargingPricingRepository;

    def setup() {
        chargingPricingRepository = Mock()
        pricingService = new PricingService(chargingPricingService, chargingPricingRepository)
    }

    def "should calculate charging pricing request"() {
        setup:
        chargingPricingService.calculateTotalPrice(_ as CustomerId, _ as ChargingTime) >> _0_EUR

        def pricingService = new PricingService(chargingPricingService, chargingPricingRepository)
        def chargingPricingRequest = chargingPricingRequest()

        when:
        ChargingPricingData pricing = pricingService.createPricing(chargingPricingRequest)

        then:
        1 * chargingPricingRepository.add(_ as ChargingPricing)
        pricing.getId() != null
        pricing.getStartCharging() == chargingPricingRequest.getStartCharging()
        pricing.getStopCharging() == chargingPricingRequest.getStopCharging()
        pricing.getCustomerId() == chargingPricingRequest.getCustomerId()
        pricing.getTotalPrice() == _0_EUR.doubleValue()
        pricing.getCurrency() == _0_EUR.currency()
    }

    def "should throw business logic exception when pass invalid data to calculation"(String customerId, Date start, Date stop) {
        given:
        def request = new ChargingPricingRequest(customerId, start, stop)

        when:
        pricingService.createPricing(request)

        then:
        thrown(BusinessLogicException)

        where:
        customerId | start             | stop
        null       | _2018_11_26_18_00 | _2018_11_26_19_00
        ""         | _2018_11_26_18_00 | _2018_11_26_19_00
        "testID"   | null              | _2018_11_26_19_00
        "testID"   | _2018_11_26_18_00 | null
        "testID"   | _2018_11_26_19_00 | _2018_11_26_18_00
    }
}

trait SpecHelper {
    def chargingPricingRequest() {
        def startCharging = new Date()
        def stopCharging = new Date()
        String customerId = "testCustomerId"

        return new ChargingPricingRequest(
                customerId,
                startCharging,
                stopCharging,
        )
    }
}
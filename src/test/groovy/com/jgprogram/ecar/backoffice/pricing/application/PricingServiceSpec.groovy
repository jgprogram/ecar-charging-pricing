package com.jgprogram.ecar.backoffice.pricing.application

import com.jgprogram.common.domain.model.Money
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicyFactory
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.NoDiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.VIPDiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory

import spock.lang.*

import static java.time.LocalDateTime.of

class PricingServiceSpec extends Specification implements SpecHelper {

    def _0_EUR = new Money(0, "EUR")
    def _9_EUR = new Money(9, "EUR")
    def _10_EUR = new Money(10, "EUR")

    PricePolicy mockPricePolicy
    PricePolicyFactory pricePolicyFactory
    DiscountPolicy mockDiscountPolicy
    DiscountPolicyFactory discountPolicyFactory
    PricingService pricingService

    def setup() {
        mockPricePolicy = Mock()
        pricePolicyFactory = Mock()
        pricePolicyFactory.create() >> mockPricePolicy
        mockDiscountPolicy = Mock()
        mockDiscountPolicy.apply(_ as Money) >> _0_EUR
        discountPolicyFactory = Mock()
        pricingService = new PricingService(pricePolicyFactory, discountPolicyFactory)
    }

    def "should calculate charging pricing request"() {
        setup:
        mockPricePolicy.apply(_ as ChargingTime) >> _0_EUR
        discountPolicyFactory.createFor(_ as CustomerId) >> mockDiscountPolicy
        def chargingPricingRequest = chargingPricingRequest()

        when:
        ChargingPricing pricing = pricingService.calculate(chargingPricingRequest)

        then:
        pricing.chargingPricingId() != null
        pricing.chargingTime().start() == chargingPricingRequest.getStartCharging()
        pricing.chargingTime().stop() == chargingPricingRequest.getStopCharging()
        pricing.customerId() == new CustomerId(chargingPricingRequest.getCustomerId())
        pricing.totalPrice() == _0_EUR
    }

    def "should calculate charging pricing request for vip customer"() {
        setup:
        mockPricePolicy.apply(_ as ChargingTime) >> _10_EUR
        discountPolicyFactory.createFor(_ as CustomerId) >> new VIPDiscountPolicy()

        expect:
        pricingService.calculate(chargingPricingRequest()).totalPrice() == _9_EUR
    }

    def "should calculate charging pricing request for normal customer"() {
        setup:
        mockPricePolicy.apply(_ as ChargingTime) >> _10_EUR
        discountPolicyFactory.createFor(_ as CustomerId) >> new NoDiscountPolicy()

        expect:
        pricingService.calculate(chargingPricingRequest()).totalPrice() == _10_EUR
    }
}

trait SpecHelper {
    def chargingPricingRequest() {
        def startCharging = of(2018, 1, 1, 20, 50)
        def stopCharging = of(2018, 1, 1, 23, 50)
        String customerId = "testCustomerId"

        return new ChargingPricingRequest(
                customerId,
                startCharging,
                stopCharging,
        )
    }
}
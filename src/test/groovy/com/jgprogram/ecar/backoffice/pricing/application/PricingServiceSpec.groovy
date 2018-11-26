package com.jgprogram.ecar.backoffice.pricing.application

import com.jgprogram.common.domain.model.Money
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricing
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingPricingRepository
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicyFactory
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.NoDiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.VIPDiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory
import com.jgprogram.ecar.backoffice.pricing.shared.ChargingPricingData
import com.jgprogram.ecar.backoffice.pricing.shared.ChargingPricingRequest
import spock.lang.*

class PricingServiceSpec extends Specification implements SpecHelper {

    def _0_EUR = new Money(0, "EUR")
    def _9_EUR = new Money(9, "EUR")
    def _10_EUR = new Money(10, "EUR")

    PricePolicy mockPricePolicy
    PricePolicyFactory pricePolicyFactory
    DiscountPolicy mockDiscountPolicy
    DiscountPolicyFactory discountPolicyFactory
    PricingService pricingService
    ChargingPricingRepository chargingPricingRepository;

    def setup() {
        chargingPricingRepository = Mock()
        mockPricePolicy = Mock()
        pricePolicyFactory = Mock()
        pricePolicyFactory.create() >> mockPricePolicy
        mockDiscountPolicy = Mock()
        mockDiscountPolicy.apply(_ as Money) >> _0_EUR
        discountPolicyFactory = Mock()
        pricingService = new PricingService(pricePolicyFactory, discountPolicyFactory, chargingPricingRepository, new ChargingPricingMapper())
    }

    def "should calculate charging pricing request"() {
        setup:
        mockPricePolicy.apply(_ as ChargingTime) >> _0_EUR
        discountPolicyFactory.createFor(_ as CustomerId) >> mockDiscountPolicy
        def chargingPricingRequest = chargingPricingRequest()

        when:
        ChargingPricingData pricing = pricingService.calculate(chargingPricingRequest)

        then:
        1 * chargingPricingRepository.add(_ as ChargingPricing)
        pricing.getId() != null
        pricing.getStartCharging() == chargingPricingRequest.getStartCharging()
        pricing.getStopCharging() == chargingPricingRequest.getStopCharging()
        pricing.getCustomerId() == chargingPricingRequest.getCustomerId()
        pricing.getTotalPrice() == _0_EUR.doubleValue()
        pricing.getCurrency() == _0_EUR.currency()
    }

    def "should calculate charging pricing request for vip customer"() {
        setup:
        mockPricePolicy.apply(_ as ChargingTime) >> _10_EUR
        discountPolicyFactory.createFor(_ as CustomerId) >> new VIPDiscountPolicy()

        expect:
        pricingService.calculate(chargingPricingRequest()).getTotalPrice() == _9_EUR.doubleValue()
    }

    def "should calculate charging pricing request for normal customer"() {
        setup:
        mockPricePolicy.apply(_ as ChargingTime) >> _10_EUR
        discountPolicyFactory.createFor(_ as CustomerId) >> new NoDiscountPolicy()

        expect:
        pricingService.calculate(chargingPricingRequest()).getTotalPrice() == _10_EUR.doubleValue()
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
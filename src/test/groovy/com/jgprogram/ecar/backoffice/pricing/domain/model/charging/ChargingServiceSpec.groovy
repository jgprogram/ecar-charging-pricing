package com.jgprogram.ecar.backoffice.pricing.domain.model.charging

import com.jgprogram.common.domain.model.Money
import com.jgprogram.ecar.backoffice.pricing.domain.model.CustomerId
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.DiscountPolicyFactory
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.NoDiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.discount.VIPDiscountPolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicy
import com.jgprogram.ecar.backoffice.pricing.domain.model.price.PricePolicyFactory
import spock.lang.Specification

class ChargingServiceSpec extends Specification{

    static _0_EUR = new Money(0, "EUR")
    static _9_EUR = new Money(9, "EUR")
    static _10_EUR = new Money(10, "EUR")
    static _2018_11_26_18_00 = new Date(1543255200000)
    static _2018_11_26_19_00 = new Date(1543258800000)

    ChargingPricingService chargingPricingService
    PricePolicy mockPricePolicy
    PricePolicyFactory pricePolicyFactory
    DiscountPolicy mockDiscountPolicy
    DiscountPolicyFactory discountPolicyFactory

    def setup() {
        mockPricePolicy = Mock()
        pricePolicyFactory = Mock()
        pricePolicyFactory.create() >> mockPricePolicy
        mockDiscountPolicy = Mock()
        mockDiscountPolicy.apply(_ as Money) >> _0_EUR
        discountPolicyFactory = Mock()
        chargingPricingService = new ChargingPricingService(pricePolicyFactory, discountPolicyFactory)
    }

    def "should calculate charging pricing request for vip customer"() {
        setup:
        def customerId = new CustomerId("1")
        def chargingTime = new ChargingTime(_2018_11_26_18_00, _2018_11_26_19_00)
        mockPricePolicy.apply(chargingTime) >> _10_EUR
        discountPolicyFactory.createFor(customerId) >> new VIPDiscountPolicy()

        expect:
        chargingPricingService.calculateTotalPrice(customerId, chargingTime) == _9_EUR
    }

    def "should calculate charging pricing request for normal customer"() {
        setup:
        def customerId = new CustomerId("2")
        def chargingTime = new ChargingTime(_2018_11_26_18_00, _2018_11_26_19_00)
        mockPricePolicy.apply(chargingTime) >> _10_EUR
        discountPolicyFactory.createFor(customerId) >> new NoDiscountPolicy()

        expect:
        chargingPricingService.calculateTotalPrice(customerId, chargingTime) == _10_EUR
    }
}

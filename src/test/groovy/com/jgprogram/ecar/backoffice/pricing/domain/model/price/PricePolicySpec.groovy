package com.jgprogram.ecar.backoffice.pricing.domain.model.price

import com.jgprogram.common.domain.model.Money
import spock.lang.*

import java.time.LocalDateTime
import java.time.LocalTime

class PricePolicySpec extends Specification {

    def beforeNoonPrice = new Money(0.05, "EUR")
    def afterNoonPrice = new Money(0.06, "EUR")
    def pricePolicy = new AbstractTimePricePolicy(Arrays.asList(
            new TimePriceRule(
                    LocalTime.of(0, 0),
                    LocalTime.of(11, 59),
                    new Money(0.05, "EUR")),
            new TimePriceRule(
                    LocalTime.of(12, 0),
                    LocalTime.of(23, 59),
                    new Money(0.06, "EUR"))
    )) {}

    def "should create time price policy contains before noon rule and afternoon rule"() {
        given:
        def beforeNoonRule = new TimePriceRule(
                LocalTime.of(0, 0), LocalTime.of(11, 59), beforeNoonPrice)
        def afterNoonRule = new TimePriceRule(
                LocalTime.of(12, 0), LocalTime.of(23, 59), afterNoonPrice)

        expect:
        pricePolicy.rules().size() == 2
        pricePolicy.rules()[0] == beforeNoonRule
        pricePolicy.rules()[1] == afterNoonRule
    }

    def "should apply before noon rule from time price policy"() {
        given:
        def dateTimeBeforeNoon = LocalDateTime.of(2018, 1, 1, 8, 30)

        when:
        def price = pricePolicy.apply(dateTimeBeforeNoon)

        then:
        price == beforeNoonPrice
    }

    def "should apply afternoon rule from time price policy"() {
        given:
        def dateTimeAfterNoon = LocalDateTime.of(2018, 1, 1, 21, 31)

        when:
        def price = pricePolicy.apply(dateTimeAfterNoon)

        then:
        price == afterNoonPrice
    }
}

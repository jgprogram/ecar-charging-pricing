package com.jgprogram.ecar.backoffice.pricing.domain.model.price

import com.jgprogram.common.domain.model.Money
import com.jgprogram.ecar.backoffice.pricing.domain.model.charging.ChargingTime
import spock.lang.*

import java.time.LocalDateTime
import java.time.LocalTime

import static java.time.LocalDateTime.of

class PricePolicySpec extends Specification {

    static beforeNoonPrice = new Money(0.05, "EUR")
    static afterNoonPrice = new Money(0.06, "EUR")

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
        def dateTimeBeforeNoon = of(2018, 1, 1, 8, 30)

        when:
        def totalPrice = pricePolicy.apply(new ChargingTime(
                dateTimeBeforeNoon,
                dateTimeBeforeNoon
        ))

        then:
        totalPrice == beforeNoonPrice
    }

    def "should apply afternoon rule from minute price policy"() {
        given:
        def dateTimeAfterNoon = of(2018, 1, 1, 21, 31)

        when:
        def totalPrice = pricePolicy.apply(new ChargingTime(
                dateTimeAfterNoon,
                dateTimeAfterNoon
        ))

        then:
        totalPrice == afterNoonPrice
    }

    def "should apply afternoon and before noon rules from minute price policy"(LocalDateTime startCharging, LocalDateTime stopCharging) {
        given:
        def chargingTime = new ChargingTime(startCharging, stopCharging)

        expect:
        pricePolicy.apply(chargingTime) == afterNoonPrice.add(beforeNoonPrice)

        where:
        startCharging          | stopCharging
        of(2018, 2, 1, 11, 59) | of(2018, 2, 1, 12, 00)
        of(2018, 2, 1, 23, 59) | of(2018, 2, 2, 0, 00)
    }


    def "should apply time price policy for different charging times"(LocalDateTime startCharging, LocalDateTime stopCharging, Money totalPrice) {
        given:
        def chargingTime = new ChargingTime(startCharging, stopCharging)

        expect:
        pricePolicy.apply(chargingTime) == totalPrice

        where:
        startCharging            | stopCharging             | totalPrice
        of(2018, 02, 01, 00, 00) | of(2018, 02, 01, 11, 59) | new Money(36, "EUR")
        of(2018, 02, 01, 12, 00) | of(2018, 02, 01, 23, 59) | new Money(43.2, "EUR")
        of(2018, 12, 01, 00, 00) | of(2018, 12, 01, 23, 59) | new Money(79.2, "EUR")
        of(2018, 12, 02, 00, 00) | of(2018, 12, 04, 00, 00) | new Money(158.45, "EUR")
    }
}

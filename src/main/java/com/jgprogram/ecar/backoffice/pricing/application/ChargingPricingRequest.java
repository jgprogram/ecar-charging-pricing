package com.jgprogram.ecar.backoffice.pricing.application;

import java.time.LocalDateTime;

public class ChargingPricingRequest {
    private String customerId;
    private LocalDateTime startCharging;
    private LocalDateTime stopCharging;

    public ChargingPricingRequest(String customerId, LocalDateTime startCharging, LocalDateTime stopCharging) {
        this.customerId = customerId;
        this.startCharging = startCharging;
        this.stopCharging = stopCharging;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getStartCharging() {
        return startCharging;
    }

    public LocalDateTime getStopCharging() {
        return stopCharging;
    }
}

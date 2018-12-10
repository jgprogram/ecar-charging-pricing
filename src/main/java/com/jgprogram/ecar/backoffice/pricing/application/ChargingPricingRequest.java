package com.jgprogram.ecar.backoffice.pricing.application;

import java.util.Date;

public class ChargingPricingRequest {
    private String customerId;
    private Date startCharging;
    private Date stopCharging;

    public ChargingPricingRequest(String customerId, Date startCharging, Date stopCharging) {
        this.customerId = customerId;
        this.startCharging = startCharging;
        this.stopCharging = stopCharging;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getStartCharging() {
        return startCharging;
    }

    public Date getStopCharging() {
        return stopCharging;
    }
}

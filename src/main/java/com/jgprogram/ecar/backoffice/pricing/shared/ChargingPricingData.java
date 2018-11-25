package com.jgprogram.ecar.backoffice.pricing.shared;

import java.util.Date;

public class ChargingPricingData {
    private String currency;
    private String customerId;
    private String id;
    private Date startCharging;
    private Date stopCharging;
    private Double totalPrice;

    public ChargingPricingData(String currency, String customerId, String id, Date startCharging, Date stopCharging, Double totalPrice) {
        this.currency = currency;
        this.customerId = customerId;
        this.id = id;
        this.startCharging = startCharging;
        this.stopCharging = stopCharging;
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getId() {
        return id;
    }

    public Date getStartCharging() {
        return startCharging;
    }

    public Date getStopCharging() {
        return stopCharging;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}

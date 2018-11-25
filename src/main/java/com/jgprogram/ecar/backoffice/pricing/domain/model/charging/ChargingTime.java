package com.jgprogram.ecar.backoffice.pricing.domain.model.charging;

import com.jgprogram.common.domain.model.ValueObject;

import java.time.LocalDateTime;

public class ChargingTime extends ValueObject {
    private LocalDateTime start;
    private LocalDateTime stop;

    public ChargingTime(LocalDateTime start, LocalDateTime stop) {
        this.start = start;
        this.stop = stop;
    }

    public LocalDateTime start() {
        return start;
    }

    public LocalDateTime stop() {
        return stop;
    }
}

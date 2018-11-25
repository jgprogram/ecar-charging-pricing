package com.jgprogram.ecar.backoffice.pricing.domain.model.charging;

import com.jgprogram.common.domain.model.ValueObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ChargingTime extends ValueObject {
    private LocalDateTime start;
    private LocalDateTime stop;

    public ChargingTime(LocalDateTime start, LocalDateTime stop) {
        this.start = start;
        this.stop = stop;
    }

    public ChargingTime(Date start, Date stop) {
        this.start = toLocalDateTime(start);
        this.stop = toLocalDateTime(stop);
    }

    public LocalDateTime start() {
        return start;
    }

    public LocalDateTime stop() {
        return stop;
    }

    public Long durationInMinutes() {
        return Duration.between(start(), stop())
                .toMinutes();
    }

    private LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}

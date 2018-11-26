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
        setStart(start);
        setStop(stop);
        assertArgumentTrue(start.compareTo(stop) <= 0,
                "Start date must be before or equals stop date.");
    }

    public ChargingTime(Date start, Date stop) {
        setStart(toLocalDateTime(start));
        setStop(toLocalDateTime(stop));
        assertArgumentTrue(start.compareTo(stop) <= 0,
                "Start date must be before or equals stop date.");
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
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setStart(LocalDateTime start) {
        assertArgumentNotNull(start, "Start date is required.");
        this.start = start;
    }

    public void setStop(LocalDateTime stop) {
        assertArgumentNotNull(stop, "Stop date is required.");
        this.stop = stop;
    }
}

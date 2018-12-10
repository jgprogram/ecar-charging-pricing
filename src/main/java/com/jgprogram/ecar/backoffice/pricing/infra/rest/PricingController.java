package com.jgprogram.ecar.backoffice.pricing.infra.rest;

import com.jgprogram.ecar.backoffice.pricing.application.ChargingPricingData;
import com.jgprogram.ecar.backoffice.pricing.application.ChargingPricingRequest;
import com.jgprogram.ecar.backoffice.pricing.application.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("pricing")
public class PricingController {

    private final PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping
    public ResponseEntity<ChargingPricingData> getPricing(
            @RequestParam String customerId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd\'T\'HH:mm") Date startCharging,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd\'T\'HH:mm") Date stopCharging
    ) {
        ChargingPricingData chargingPricingData = pricingService.calculate(
                new ChargingPricingRequest(
                        customerId,
                        startCharging,
                        stopCharging
                ));

        return ResponseEntity.ok(chargingPricingData);
    }


}

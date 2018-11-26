package com.jgprogram.ecar.backoffice.pricing.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("in-memory")
@AutoConfigureMockMvc
public class PricingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void when_getPricingForCustomer_then_statusOkAndJSONHasChargingPricingData() throws Exception {
        String customerId = "testCustomerId";
        String startCharging = "2018-10-25T11:59";
        String stopCharging = "2018-10-25T12:00";

        mockMvc.perform(
                get("/pricing")
                        .param("customerId", customerId)
                        .param("startCharging", startCharging)
                        .param("stopCharging", stopCharging))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andExpect(jsonPath("$.customerId", is(customerId)))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.startCharging", is(startCharging)))
                .andExpect(jsonPath("$.stopCharging", is(stopCharging)))
                .andExpect(jsonPath("$.totalPrice", is(0.11)));
    }

    @Test
    public void when_getPricingForVipCustomer_then_statusOkAndJSONHasChargingPricingData() throws Exception {
        String customerId = "VIP-ID";
        String startCharging = "2018-10-25T11:58";
        String stopCharging = "2018-10-25T11:59";

        mockMvc.perform(
                get("/pricing")
                        .param("customerId", customerId)
                        .param("startCharging", startCharging)
                        .param("stopCharging", stopCharging))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andExpect(jsonPath("$.customerId", is(customerId)))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.startCharging", is(startCharging)))
                .andExpect(jsonPath("$.stopCharging", is(stopCharging)))
                .andExpect(jsonPath("$.totalPrice", is(0.09)));
    }
}

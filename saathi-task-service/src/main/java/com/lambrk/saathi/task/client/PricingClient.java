package com.lambrk.saathi.task.client;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PricingClient {
    public BigDecimal estimate(Long serviceCategoryId, double distanceKm) {
        return BigDecimal.ZERO;
    }
}

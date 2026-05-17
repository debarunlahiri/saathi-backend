package com.lambrk.saathi.pricing.dto;

import java.math.BigDecimal;

public record PricingEstimateResponse(
    BigDecimal basePrice,
    BigDecimal distanceCharge,
    BigDecimal waitingCharge,
    BigDecimal urgencyCharge,
    BigDecimal platformFee,
    BigDecimal discount,
    BigDecimal finalPrice) {}

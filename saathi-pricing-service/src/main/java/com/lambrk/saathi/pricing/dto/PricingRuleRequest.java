package com.lambrk.saathi.pricing.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PricingRuleRequest(
        @NotNull Long serviceCategoryId,
        @NotNull BigDecimal basePrice,
        BigDecimal includedDistanceKm,
        BigDecimal perKmCharge,
        Integer includedWaitingMinutes,
        Integer perWaitingUnitMinutes,
        BigDecimal perWaitingUnitCharge,
        BigDecimal urgentCharge,
        BigDecimal platformFee,
        Boolean active
) {
}

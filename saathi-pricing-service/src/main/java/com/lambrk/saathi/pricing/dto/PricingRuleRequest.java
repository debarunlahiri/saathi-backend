package com.lambrk.saathi.pricing.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record PricingRuleRequest(
    @NotNull UUID serviceCategoryId,
    @NotNull BigDecimal basePrice,
    BigDecimal includedDistanceKm,
    BigDecimal perKmCharge,
    Integer includedWaitingMinutes,
    Integer perWaitingUnitMinutes,
    BigDecimal perWaitingUnitCharge,
    BigDecimal urgentCharge,
    BigDecimal platformFee,
    Boolean active) {}

package com.lambrk.saathi.pricing.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record PricingEstimateRequest(
    @NotNull UUID serviceCategoryId,
    BigDecimal distanceKm,
    Integer waitingMinutes,
    boolean urgent,
    BigDecimal discount) {}

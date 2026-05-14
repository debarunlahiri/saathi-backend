package com.lambrk.saathi.pricing.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PricingEstimateRequest(@NotNull Long serviceCategoryId, BigDecimal distanceKm, Integer waitingMinutes, boolean urgent, BigDecimal discount) {
}

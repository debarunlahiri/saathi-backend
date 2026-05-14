package com.lambrk.saathi.location.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LocationUpdateRequest(@NotNull Long taskId, @NotNull Long partnerId, @NotNull BigDecimal latitude, @NotNull BigDecimal longitude, BigDecimal accuracy) {
}

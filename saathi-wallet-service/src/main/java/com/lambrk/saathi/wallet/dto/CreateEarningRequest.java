package com.lambrk.saathi.wallet.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateEarningRequest(@NotNull Long partnerId, @NotNull Long taskId, @NotNull BigDecimal grossAmount, @NotNull BigDecimal platformCommission) {
}

package com.lambrk.saathi.wallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PayoutRequest(
    @NotNull Long partnerId, @NotNull BigDecimal amount, @NotBlank String payoutMethod) {}

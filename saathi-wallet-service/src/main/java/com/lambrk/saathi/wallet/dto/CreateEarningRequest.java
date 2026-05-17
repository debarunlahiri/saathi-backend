package com.lambrk.saathi.wallet.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record CreateEarningRequest(
    @NotNull UUID partnerId,
    @NotNull UUID taskId,
    @NotNull BigDecimal grossAmount,
    @NotNull BigDecimal platformCommission) {}

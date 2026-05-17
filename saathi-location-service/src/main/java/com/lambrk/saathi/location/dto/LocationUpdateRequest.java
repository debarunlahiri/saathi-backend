package com.lambrk.saathi.location.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record LocationUpdateRequest(
    @NotNull UUID taskId,
    @NotNull UUID partnerId,
    @NotNull BigDecimal latitude,
    @NotNull BigDecimal longitude,
    BigDecimal accuracy) {}

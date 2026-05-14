package com.lambrk.saathi.partner.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LocationRequest(@NotNull BigDecimal latitude, @NotNull BigDecimal longitude) {
}

package com.lambrk.saathi.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServiceCategoryRequest(@NotBlank String name, @NotBlank String code, String description, String iconUrl, @NotNull BigDecimal basePrice, Boolean active) {
}

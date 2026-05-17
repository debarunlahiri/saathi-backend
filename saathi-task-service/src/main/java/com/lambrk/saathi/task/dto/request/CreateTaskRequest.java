package com.lambrk.saathi.task.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateTaskRequest(
    @NotNull UUID customerId,
    @NotNull UUID serviceCategoryId,
    @NotBlank String title,
    String description,
    @NotBlank String pickupAddress,
    BigDecimal pickupLatitude,
    BigDecimal pickupLongitude,
    String dropAddress,
    OffsetDateTime taskDateTime,
    BigDecimal estimatedPrice) {}

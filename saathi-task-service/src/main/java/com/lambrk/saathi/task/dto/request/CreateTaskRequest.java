package com.lambrk.saathi.task.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CreateTaskRequest(
        @NotNull Long customerId,
        @NotNull Long serviceCategoryId,
        @NotBlank String title,
        String description,
        @NotBlank String pickupAddress,
        BigDecimal pickupLatitude,
        BigDecimal pickupLongitude,
        String dropAddress,
        OffsetDateTime taskDateTime,
        BigDecimal estimatedPrice
) {
}

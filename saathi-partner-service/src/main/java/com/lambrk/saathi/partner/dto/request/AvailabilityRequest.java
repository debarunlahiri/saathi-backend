package com.lambrk.saathi.partner.dto.request;

import com.lambrk.saathi.partner.enums.AvailabilityStatus;
import jakarta.validation.constraints.NotNull;

public record AvailabilityRequest(@NotNull AvailabilityStatus availabilityStatus) {}

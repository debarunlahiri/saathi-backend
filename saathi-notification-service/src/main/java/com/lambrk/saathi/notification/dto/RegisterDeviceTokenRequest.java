package com.lambrk.saathi.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RegisterDeviceTokenRequest(@NotNull UUID userId, @NotBlank String deviceToken) {}

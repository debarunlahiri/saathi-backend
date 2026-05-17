package com.lambrk.saathi.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDeviceTokenRequest(@NotNull Long userId, @NotBlank String deviceToken) {}

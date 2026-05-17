package com.lambrk.saathi.customer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CustomerProfileRequest(
    @NotNull Long identityUserId,
    @NotBlank String fullName,
    @NotBlank String mobileNumber,
    String email,
    String defaultAddress,
    BigDecimal defaultLatitude,
    BigDecimal defaultLongitude,
    String emergencyContact) {}

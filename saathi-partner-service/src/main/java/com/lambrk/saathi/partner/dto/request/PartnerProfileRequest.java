package com.lambrk.saathi.partner.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PartnerProfileRequest(
    @NotNull Long identityUserId,
    @NotBlank String fullName,
    @NotBlank String mobileNumber,
    String email,
    String address,
    BigDecimal serviceRadiusKm) {}

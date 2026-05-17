package com.lambrk.saathi.partner.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record PartnerProfileRequest(
    @NotNull UUID identityUserId,
    @NotBlank String fullName,
    @NotBlank String mobileNumber,
    String email,
    String address,
    BigDecimal serviceRadiusKm) {}

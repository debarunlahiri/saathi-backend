package com.lambrk.saathi.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record VerifyPaymentRequest(
    @NotNull UUID paymentId, @NotBlank String gatewayPaymentId, @NotBlank String signature) {}

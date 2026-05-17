package com.lambrk.saathi.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VerifyPaymentRequest(
    @NotNull Long paymentId, @NotBlank String gatewayPaymentId, @NotBlank String signature) {}

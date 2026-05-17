package com.lambrk.saathi.payment.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentOrderRequest(
    @NotNull UUID taskId,
    @NotNull UUID customerId,
    @NotNull BigDecimal amount,
    String paymentGateway) {}

package com.lambrk.saathi.payment.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreatePaymentOrderRequest(
    @NotNull Long taskId,
    @NotNull Long customerId,
    @NotNull BigDecimal amount,
    String paymentGateway) {}
